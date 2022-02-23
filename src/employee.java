public class employee{
    private String name;
    private String surname;
    private String position;
    private int seniority;
    private int solary;

    public employee(String name, String surname, String position, int seniority, int solary) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.seniority = seniority;
        this.solary = solary;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public int getSeniority() {
        return seniority;
    }

    public int getSolary() {
        return solary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public void setSolary(int solary) {
        this.solary = solary;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + position + " " + seniority + " " + solary;
    }
}
