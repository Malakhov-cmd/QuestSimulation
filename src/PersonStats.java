public class PersonStats {
    private int fatigue;
    private int stress;
    private int endurance;
    private int hourPast;
    private int brave;

    public PersonStats(int fatigue, int stress, int endurance, int brave)
    {
        this.fatigue = fatigue;
        this.stress = stress;
        this.endurance = endurance;
        this.brave = brave;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getHour()
    {
        return hourPast;
    }

    public void setHourPast(int newHourCount)
    {
        this.hourPast = newHourCount;
    }

    public int getBrave()
    {
        return brave;
    }

    public void setBrave(int newBrave)
    {
        this.brave = newBrave;
    }
}
