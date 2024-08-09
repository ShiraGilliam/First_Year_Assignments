public class MoltOrder implements Comparable<MoltOrder>{
    private String name;
    private String orderDescription;
    private int orderReadyTime;
    private int timeNeededToDeliver;
    private int priority;

    public MoltOrder(String name, String orderDescription, int orderReadyTime, int timeNeededToDeliver, int priority){
        this.name = name;
        this.orderDescription = orderDescription;
        this.orderReadyTime = orderReadyTime;
        this.timeNeededToDeliver = timeNeededToDeliver;
        this.priority = priority;
    }

    public String toString(){
        return "MoltOrder{" +
          "name='" + name + '\'' +
          ", order Description=" + orderDescription +
          ", order Ready Time=" + orderReadyTime +
          ", time Needed To Deliver=" + timeNeededToDeliver +
          ", priority=" + priority + '\'' +
                '}';
    }

    public int getOrderReadyTime(){
        return this.orderReadyTime;
    }

    public int getTimeNeededToDeliver(){
        return this.timeNeededToDeliver;
    }

    public String getName(){
        return this.name;
    }

    public String getOrderDescription(){
        return this.orderDescription;
    }

    public int getPriority(){
        return this.priority;
    }

    @Override
    public int compareTo(MoltOrder otherOrder) {
        return this.priority - otherOrder.getPriority();
    }
}
