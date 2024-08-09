public class MoltDriver implements Comparable<MoltDriver> {
    private int id;
    private String name;
    private int nextAvailableTimeForDelivery;
    private int totalOrderDelivery;

    public MoltDriver (int id, String name, int nextAvailableTimeForDelivery){
        this.id = id;
        this.name = name;
        this.nextAvailableTimeForDelivery = nextAvailableTimeForDelivery;
        this.totalOrderDelivery = 0;
    }

    public void incrementTotalOrdersDelivered(){
        this.totalOrderDelivery++;
    }

    public int getNextAvailableTimeForDelivery(){
        return this.nextAvailableTimeForDelivery;
    }

    public void setNextAvailableTimeForDelivery(int time){
        this.nextAvailableTimeForDelivery = time;

    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return "MoltDriver{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", next available time for delivery =" + nextAvailableTimeForDelivery + '\'' +
                '}';
    }

    @Override
    public int compareTo(MoltDriver otherDriver){
        return this.nextAvailableTimeForDelivery - otherDriver.getNextAvailableTimeForDelivery();
    }
}
