package sweeper;

class Flag {

    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes--;
    }


    public void toggleFlaggedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGGED -> setClosedToBox(coord);
            case CLOSED -> setFlagedToBox(coord);
        }
    }

    void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGGED);
    }

    int getCountOfCloseBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToClosedBombBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED) {
            flagMap.set(coord, Box.OPENED);
        }
    }

    void setNo_bombToFlaggedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGGED) {
            flagMap.set(coord, Box.NO_BOMB);
        }
    }


    int getCountOfFlaggedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord)){
            if (flagMap.get(around) == Box.FLAGGED){
                count++;
            }
        }
        return count;
    }
}
