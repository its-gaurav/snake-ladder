package models;

public class Snake {
    private Integer head;
    private Integer tail;

    public Snake(Integer head, Integer tail) {
        this.head = head;
        this.tail = tail;
    }

    public Integer getHead() {
        return head;
    }

    public Integer getTail() {
        return tail;
    }
}
