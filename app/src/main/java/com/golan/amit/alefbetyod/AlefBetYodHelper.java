package com.golan.amit.alefbetyod;

public class AlefBetYodHelper {

    private static final String[] HEBCHARS = {
      "א", "ב", "ג", "ד", "ה", "ו", "ז", "ח", "ט", "י"
    };
    private int _runner;

    public AlefBetYodHelper(int _runner) {
        this._runner = _runner;
    }

    public AlefBetYodHelper() {
        _runner = 0;
    }

    public int get_runner() {
        return _runner;
    }

    public void set_runner(int _runner) {
        this._runner = _runner;
    }

    public void increaseRunner() {
        this._runner++;
    }

    public void decreaseRunner() {
        this._runner--;
    }

    public String sequenceRepresentation() {
        if(this._runner == 0) {
            return "א";
        } else  if(this._runner == 1) {
            return "א ב";
        } else {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i <= this._runner; i++) {
                sb.append(HEBCHARS[i]);
                if(i < (this._runner)) {
                    sb.append(" ");
                }
            }
            return sb.toString();
        }
    }
}
