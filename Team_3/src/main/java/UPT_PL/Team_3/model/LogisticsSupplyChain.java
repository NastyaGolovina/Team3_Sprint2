package UPT_PL.Team_3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The LogisticsSupplyChain class represents a supply chain between two logistics sites.
 */
@Entity
@Table(name = "Logistics_Supply_Chain")
public class LogisticsSupplyChain {
	@Id
    @Column(name = "chain_id", length = 40, nullable = false)
    private String chainId;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_site_id", nullable = false)
    private LogisticsSite sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver", nullable = false)
    private LogisticsSite receiver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Transport_Id", nullable = false)
    private Transport transport;

    @Column(name = "cost_per_ton", nullable = false)
    private double costPerTon;

    @Column(name = "duration_in_days", nullable = false)
    private double durationInDays;
    
    //JUST FOR TEST!THEN REMOVE!!!
    // Список для хранения цепей в памяти
    //private static final List<LogisticsSupplyChain> chains = new ArrayList<>();

    // Список для хранения логистических сайтов в памяти
    //private static final List<LogisticsSite> logisticsSites = new ArrayList<>();
    
    
    /**
     * Constructor to initialize a LogisticsSupplyChain with all necessary details.
     *
     * @param chainId        unique identifier of the supply chain
     * @param sender         the logistics site sending the goods
     * @param receiver       the logistics site receiving the goods
     * @param transport      the mode of transport used
     * @param costPerTon     the cost per ton of transporting goods
     * @param durationInDays the duration of the supply chain in days
     */
    public LogisticsSupplyChain(String chainId, LogisticsSite sender, LogisticsSite receiver, Transport transport, double costPerTon, double durationInDays) {
        this.chainId = chainId;
        this.sender = sender;
        this.receiver = receiver;
        this.transport = transport;
        this.costPerTon = costPerTon;
        this.durationInDays = durationInDays;
    }
    
    public LogisticsSupplyChain() {
    	
    }
    
    

    /**
     * Gets the unique identifier of the supply chain.
     *
     * @return the chain ID
     */
    public String getChainId() {
        return chainId;
    }

    /**
     * Sets the unique identifier of the supply chain.
     *
     * @param chainId the new chain ID
     */
    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    /**
     * Gets the sender's logistics site.
     *
     * @return the sender's site
     */
    public LogisticsSite getSender() {
        return sender;
    }

    /**
     * Sets the sender's logistics site.
     *
     * @param sender the new sender's site
     */
    public void setSender(LogisticsSite sender) {
        this.sender = sender;
    }

    /**
     * Gets the receiver's logistics site.
     *
     * @return the receiver's site
     */
    public LogisticsSite getReceiver() {
        return receiver;
    }

    /**
     * Sets the receiver's logistics site.
     *
     * @param receiver the new receiver's site
     */
    public void setReceiver(LogisticsSite receiver) {
        this.receiver = receiver;
    }

    /**
     * Gets the transport method used in the supply chain.
     *
     * @return the transport method
     */
    public Transport getTransport() {
        return transport;
    }

    /**
     * Sets the transport method for the supply chain.
     *
     * @param transport the new transport method
     */
    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    /**
     * Gets the cost per ton of goods transported.
     *
     * @return the cost per ton
     */
    public double getCostPerTon() {
        return costPerTon;
    }

    /**
     * Sets the cost per ton of goods transported.
     *
     * @param costPerTon the new cost per ton
     */
    public void setCostPerTon(double costPerTon) {
        this.costPerTon = costPerTon;
    }

    /**
     * Gets the duration of the supply chain in days.
     *
     * @return the duration in days
     */
    public double getDurationInDays() {
        return durationInDays;
    }

    /**
     * Sets the duration of the supply chain in days.
     *
     * @param durationInDays the new duration in days
     */
    public void setDurationInDays(double durationInDays) {
        this.durationInDays = durationInDays;
    }
    
    /**
     * Checks if both the sender and receiver logistics sites exist.
     * This method ensures that both the sender and receiver are not null.
     *
     * @return true if both logistics sites (sender and receiver) exist, false otherwise
     */
    
    public boolean isLogisticsSitesValid() {
        return sender != null && receiver != null;
    }

	@Override
	public String toString() {
		return "LogisticsSupplyChain [chainId=" + chainId + ", sender=" + sender + ", receiver=" + receiver
				+ ", transport=" + transport + ", costPerTon=" + costPerTon + ", durationInDays=" + durationInDays
				+ "]";
	}
	
	
	 // Новые методы для работы с массивом

    /**
     * Добавление новой цепи в массив. Возвращает объект или null, если произошла ошибка.
     * Checks if the chain is valid before adding to the in-memory list.
     *
     * @param chain The logistics supply chain to add.
     * @return The added chain object or null if the chain is invalid.
     */
	/*
	 * Операции полностью изолированы от базы данных, работают только с массивом. !ТЗ ВЫПОЛНЕНО*/
	/*Метод addToMemory возвращает:
Объект цепи (LogisticsSupplyChain), если добавление успешно.
null, если объект не валиден (например, chain == null или логистические сайты не указаны). !ТЗ ВЫПОЛНЕНО!*/
    public static LogisticsSupplyChain addToMemory(LogisticsSupplyChain chain) {
        if (chain != null && chain.isLogisticsSitesValid()) {
            chains.add(chain);
            return chain; // Возвращаем добавленный объект
        }
        return null; // Если объект невалиден
    }

    /**
     * Удаление цепи из массива по chainId.
     * Removes a supply chain from the in-memory list by its chainId.
     * Returns true if the chain was successfully removed, false otherwise.
     *
     * @param chainId The unique ID of the supply chain to remove.
     * @return true if the chain was removed, false otherwise.
     */
    /*
	 * Операции полностью изолированы от базы данных, работают только с массивом. !ТЗ ВЫПОЛНЕНО*/
    /*
     * Возвращает true, если удаление прошло успешно.
Возвращает false, если ничего не удалено. !ТЗ ВЫПОЛНЕНО!
*/
    public static boolean removeFromMemory(String chainId) {
        return chains.removeIf(chain -> chain.getChainId().equals(chainId)); 
    }

    /**
     * Получение всех цепей из массива.
     * Retrieves all supply chains from the in-memory list.
     *
     * @return A list of all logistics supply chains in memory.
     */
    
    /*
     * Метод работает только с массивом chains и возвращает его копию. !ТЗ ВЫПОЛНЕНО!*/
    public static List<LogisticsSupplyChain> getAllFromMemory() {
        return new ArrayList<>(chains);
    }

    // Новый метод для удаления LogisticsSite

    /**
     * Удаление логистического сайта, если к нему не привязаны объекты Transport.
     * Checks for associated transport objects before deleting the logistics site.
     *
     * @param siteId The unique ID of the logistics site to remove.
     * @return true if the site was removed, false if transports are associated or site not found.
     */
    /*
     * . Метод проверяет наличие транспорта, связанного с сайтом. Если транспорты найдены, сайт не удаляется.! ТЗ ВЫПОЛНЕНО!*/
    public static boolean removeLogisticsSiteIfNoTransports(String siteId) {
        // Проверяем, есть ли транспорты, связанные с этим логистическим сайтом
        boolean hasTransports = chains.stream().anyMatch(chain -> 
            chain.getSender().getSiteId().equals(siteId) || 
            chain.getReceiver().getSiteId().equals(siteId)
        );

        // Если транспорты найдены, удаление запрещено
        if (hasTransports) {
            return false;
        }

        // Иначе удаляем логистический сайт из списка
        return logisticsSites.removeIf(site -> site.getSiteId().equals(siteId));
    }
    //ВСЕ ТЗ ПОЛНОСТЮ ВЫПОЛНЕНЫ!!
	
}