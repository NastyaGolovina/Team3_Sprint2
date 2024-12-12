package UPT_PL.Team_3.service;

import UPT_PL.Team_3.dto.LogisticsSupplyChainDto;
import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3.model.LogisticsSupplyChain;
import UPT_PL.Team_3.model.Transport;
import UPT_PL.Team_3.repository.LogisticsSiteRepository;
import UPT_PL.Team_3.repository.LogisticsSupplyChainRepository;
import UPT_PL.Team_3.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogisticsSupplyChainService {

    private final LogisticsSupplyChainRepository supplyChainRepository;
    private final LogisticsSiteRepository logisticsSiteRepository;
    private final TransportRepository transportRepository;

    public List<LogisticsSupplyChain> getAllSupplyChains() {
        return supplyChainRepository.findAll();
    }

    public Optional<LogisticsSupplyChain> getSupplyChainById(String id) {
        return supplyChainRepository.findById(id);
    }

    public LogisticsSupplyChain createSupplyChain(LogisticsSupplyChainDto supplyChainDto) {
        LogisticsSite sender = logisticsSiteRepository.findById(supplyChainDto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender site not found with id: " + supplyChainDto.getSenderId()));
        LogisticsSite receiver = logisticsSiteRepository.findById(supplyChainDto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver site not found with id: " + supplyChainDto.getReceiverId()));
        Transport transport = transportRepository.findById(supplyChainDto.getTransportId())
                .orElseThrow(() -> new RuntimeException("Transport not found with id: " + supplyChainDto.getTransportId()));

        LogisticsSupplyChain supplyChain = new LogisticsSupplyChain();
        supplyChain.setSender(sender);
        supplyChain.setReceiver(receiver);
        supplyChain.setTransport(transport);
        supplyChain.setCostPerTon(Double.parseDouble(supplyChainDto.getCostPerTon()));
        supplyChain.setDurationInDays(Double.parseDouble(supplyChainDto.getDurationInDays()));

        return supplyChainRepository.save(supplyChain);
    }

    public void deleteSupplyChain(String id) {
        if (supplyChainRepository.existsById(id)) {
            supplyChainRepository.deleteById(id);
        } else {
            throw new RuntimeException("Supply chain not found with id: " + id);
        }
    }

    public Optional<LogisticsSupplyChain> updateSupplyChain(String id, LogisticsSupplyChainDto supplyChainDto) {
        return supplyChainRepository.findById(id).map(existingChain -> {
            LogisticsSite sender = logisticsSiteRepository.findById(supplyChainDto.getSenderId())
                    .orElseThrow(() -> new RuntimeException("Sender site not found with id: " + supplyChainDto.getSenderId()));
            LogisticsSite receiver = logisticsSiteRepository.findById(supplyChainDto.getReceiverId())
                    .orElseThrow(() -> new RuntimeException("Receiver site not found with id: " + supplyChainDto.getReceiverId()));
            Transport transport = transportRepository.findById(supplyChainDto.getTransportId())
                    .orElseThrow(() -> new RuntimeException("Transport not found with id: " + supplyChainDto.getTransportId()));

            existingChain.setSender(sender);
            existingChain.setReceiver(receiver);
            existingChain.setTransport(transport);
            existingChain.setCostPerTon(Double.parseDouble(supplyChainDto.getCostPerTon()));
            existingChain.setDurationInDays(Double.parseDouble(supplyChainDto.getDurationInDays()));

            return supplyChainRepository.save(existingChain);
        });
    }
}
