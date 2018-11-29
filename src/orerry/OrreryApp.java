package orerry;

import CH.ifa.draw.application.DrawApplication;
import CH.ifa.draw.framework.Tool;
import CH.ifa.draw.tool.ConnectionTool;
import CH.ifa.draw.tool.CreationTool;

import javax.swing.*;

public class OrreryApp extends DrawApplication {
    public static void main(String[] args){
        DrawApplication window = new OrreryApp();
        window.open();
    }

    @Override
    protected void createTools(JPanel palette){
        super.createTools(palette);
        Tool tool = new CreationTool(view(), new PlanetFigure());
        palette.add(createToolButton( IMAGES + "ELLIPSE", "Add planet", tool));
        tool = new CreationTool(view(), new SatelliteFigure());
        palette.add(createToolButton( IMAGES + "SAT", "Add satellite", tool));
        tool = new ConnectionTool(view(), new GravityConnection());
        palette.add(createToolButton( IMAGES + "CONN", "Add gravity", tool));
    }
}
