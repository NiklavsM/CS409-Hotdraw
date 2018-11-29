package orerry;

import CH.ifa.draw.figure.EllipseFigure;
import CH.ifa.draw.framework.Handle;
import CH.ifa.draw.handle.BoxHandleKit;
import CH.ifa.draw.handle.ConnectionHandle;
import CH.ifa.draw.locator.RelativeLocator;

import java.awt.*;
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
        Vector<Handle> handles = new Vector<>();
        handles.add(BoxHandleKit.southEast(this));
        handles.addElement(new ConnectionHandle(this, RelativeLocator.east(),
                new GravityConnection())
        );
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
    public Color getFillColor() {
        Rectangle rect = displayBox();
        int size = Math.max(rect.width, rect.height);
        if (size <= 40) {
            return Color.white;
        } else if (size <= 80) {
            return Color.green;
        } else
            return Color.red;
    }


}
