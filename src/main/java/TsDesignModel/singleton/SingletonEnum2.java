package TsDesignModel.singleton;


public enum SingletonEnum2 {
    SINGLETON;
    private String name;
    private boolean isOk;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    @Override
    public String toString() {
        return "SingletonEnum2{" +
                "name='" + name + '\'' +
                ", isOk=" + isOk +
                '}';
    }
}
