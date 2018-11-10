package orerry;

import CH.ifa.draw.connector.ChopEllipseConnector;
import CH.ifa.draw.figure.AbstractFigure;
import CH.ifa.draw.figure.AttributeFigure;
import CH.ifa.draw.figure.CompositeFigure;
import CH.ifa.draw.figure.EllipseFigure;
import CH.ifa.draw.framework.Connector;
import CH.ifa.draw.framework.Handle;
import CH.ifa.draw.handle.BoxHandleKit;
import CH.ifa.draw.storable.StorableInput;
import CH.ifa.draw.storable.StorableOutput;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class PlanetFigure extends EllipseFigure {

    /*
     * Serialization support.
     */
    private static final long serialVersionUID = -6856203289355118951L;

    PlanetFigure() {
        super();
    }

    public Vector<Handle> handles() {
        Vector<Handle> handles = new Vector<Handle>();
        handles.add(BoxHandleKit.southEast(this));
        return handles;
    }

    public void basicDisplayBox(Point origin, Point corner) {
        int width = corner.x - origin.x;
        int height = corner.y - origin.y;
        int size = Math.max(width, height);
        int sizeABS = Math.abs(size);
        if (sizeABS < 120) {
            corner = new Point(origin.x + size, origin.y + size);
            super.basicDisplayBox(origin, corner);
        }
    }

    // Override AttributeFigure getFillColour()
    public Color getFillColor(){
        Rectangle rect = displayBox();
        int size = Math.max(rect.width, rect.height);
        if(size <= 40) {
            return Color.white;
        }
        else if(size <= 80){
            return Color.green;
        }
        else
            return Color.red;
    }


}
