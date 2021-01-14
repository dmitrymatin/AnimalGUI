package shared;

import java.awt.*;

public class FormLogger implements Logger {

    private TextArea textArea;

    public FormLogger(TextArea textArea) {
        this.textArea = textArea;
    }

    public void logMessage(String message) {
        if (textArea.getText().length() > 0)
            textArea.append("\n");
        textArea.append(message);
    }

    public void clearLog() {
        textArea.setText("");
    }
}