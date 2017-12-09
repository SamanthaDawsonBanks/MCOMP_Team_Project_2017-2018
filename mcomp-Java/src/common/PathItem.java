package common;

public class PathItem {
    
    private Waypoint data;
    private PathItem next;
    
    public PathItem(Waypoint data) {
        this.data = data;
        this.next = null;
    }
    public Waypoint getData() {
        return data;
    }
    public PathItem getNext() {
        return next;
    }
    public void setNext(PathItem next) {
        this.next = next;
    }
    
    public String toString() {
        return String.format("[%s,%d]", data.toString());
    }

}

