package com.example.nukeseeker;



public class Mine {
    private boolean revealed=false;
    private boolean present=false;
    private boolean showsText = false;


    public Mine(boolean revealed, boolean present) {
        this.revealed = revealed;
        this.present = present;
        this.showsText=false;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed() {
        this.revealed = true;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
    //ALT + INSERT
    public void showText() {
        this.showsText = true;
    }
    public boolean showsText() {
        return showsText;
    }


}
