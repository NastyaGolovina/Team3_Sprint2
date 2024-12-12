package UPT_PL.Team_3_GraphUI;

class LogisticsSupplyChainDto {
	private String chainId;
	private String senderId;
	private String receiverId;
	private String transportId;
	private String costPerTon;
	private String durationInDays;
	
	public LogisticsSupplyChainDto() {
    }
    
    public LogisticsSupplyChainDto(String senderId, String receiverId, String transportId, String costPerTon, String durationInDays) {
    	this.chainId = chainId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.transportId = transportId;
        this.costPerTon = costPerTon;
        this.durationInDays = durationInDays;
    }
    
	public String getChainId() {
		return chainId;
	}
	
	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
    
    public String getSenderId() {
        return senderId;
    }
    
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    
    public String getReceiverId() {
        return receiverId;
    }
    
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
    
    public String getTransportId() {
        return transportId;
    }
    
    public void setTransportId(String transportId) {
        this.transportId = transportId;
    }
    
    public String getCostPerTon() {
        return costPerTon;
    }
    
    public void setCostPerTon(String costPerTon) {
        this.costPerTon = costPerTon;
    }
    
    public String getDurationInDays() {
        return durationInDays;
    }
    
    public void setDurationInDays(String durationInDays) {
        this.durationInDays = durationInDays;
    }
   
    
}