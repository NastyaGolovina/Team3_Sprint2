package UPT_PL.Team_3.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LogisticsSupplyChainDto {
    private String senderId;
    private String receiverId;
    private String transportId;
    private String costPerTon;
    private String durationInDays;
}