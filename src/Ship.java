public class Ship {
    int HP = 3;
    int size = 2;
    String name = " \uD83D\uDEA2";

    public void hit() {
        this.HP -= 1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
