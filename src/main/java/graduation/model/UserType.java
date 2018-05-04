package graduation.model;

public enum UserType {
    USER(0), ADMIN(1);
    private int id;

    UserType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
