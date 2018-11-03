package practical3;

import CH.ifa.draw.application.DrawApplication;
import CH.ifa.draw.figure.connection.LineConnection;
import CH.ifa.draw.framework.Tool;
import CH.ifa.draw.samples.net.NodeFigure;
import CH.ifa.draw.samples.pert.PertFigureCreationTool;
import CH.ifa.draw.tool.ConnectionTool;
import CH.ifa.draw.tool.CreationTool;
import CH.ifa.draw.tool.TextTool;

import javax.swing.*;

public class NikApp extends DrawApplication {
    public static void main(String[] args) {
        DrawApplication window = new NikApp();
        window.open();
    }

    @Override
    protected void createTools(JPanel palette) {
        super.createTools(palette);
        Tool tool = new PertFigureCreationTool(view());
        palette.add(createToolButton("NoImage", "PertFigure", tool));
    }
}
