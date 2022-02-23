public enum employeePosition {
    it(4000,6000),
    manager(2000,4000);

    private final int solaryMin;
    private final int solaryMax;

    employeePosition(int solaryMin, int solaryMax) {
        this.solaryMin = solaryMin;
        this.solaryMax = solaryMax;
    }
    public static boolean checkSolary(String position, int solary){
        for( employeePosition ep : employeePosition.values()){
            if(ep.name().equals(position)) {
                if (ep.solaryMin <= solary && ep.solaryMax >= solary)
                    return true;
            }
        }
        return false;
    }
}
