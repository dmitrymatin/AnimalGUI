package shared;

import java.awt.*;

public class FormLogger implements Logger {

    private TextArea textArea;

    public FormLogger(TextArea textArea) {
        this.textArea = textArea;
    }

    public void logMessage(String message) {
        textArea.append("\n" + message);
    }

    public void clearLog() {
        textArea.setText("");
    }
}