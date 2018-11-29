package orerry;

import CH.ifa.draw.figure.CompositeFigure;
import CH.ifa.draw.figure.EllipseFigure;
import CH.ifa.draw.figure.RectangleFigure;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.framework.Handle;
import CH.ifa.draw.handle.ConnectionHandle;
import CH.ifa.draw.handle.NullHandle;
import CH.ifa.draw.locator.RelativeLocator;

import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

public class SatelliteFigure extends CompositeFigure {

    private Rectangle fDisplayBox;

    public SatelliteFigure() {
        initialize();
    }

    public void initialize() {
        fDisplayBox = new Rectangle(0, 0, 0, 0);
        System.out.println("fDisplayBox.x " + fDisplayBox.x);
        System.out.println("fDisplayBox.y " + fDisplayBox.y);
        RectangleFigure rectangle1 = new RectangleFigure(new Point(0, 0), new Point(10, 20));
        EllipseFigure ellipse = new EllipseFigure(new Point(0,0), new Point(10,10));
        RectangleFigure rectangle2 = new RectangleFigure(new Point(0, 0), new Point(10, 20));
        add(rectangle1);
        add(ellipse);
        add(rectangle2);
    }

    @Override
    public void basicDisplayBox(Point origin, Point corner) {
        System.out.println("fDisplayBox.x 1  " + fDisplayBox.x);
        System.out.println("fDisplayBox.y " + fDisplayBox.y);
        fDisplayBox = new Rectangle(origin);
        fDisplayBox.width = 10;
        fDisplayBox.height = 50;

        layout();
    }

    private void layout() {
        Point partOrigin = new Point(fDisplayBox.x, fDisplayBox.y);

        Enumeration<Figure> k = figures();
        while (k.hasMoreElements()) {
            Figure f = k.nextElement();

            Dimension partExtent = f.size();
            Point corner = new Point(
                    partOrigin.x+partExtent.width,
                    partOrigin.y+partExtent.height);
            f.basicDisplayBox(partOrigin, corner);
            partOrigin.y += partExtent.height;
        }
    }

    protected void basicMoveBy(int x, int y) {

        fDisplayBox.translate(x, y);
        super.basicMoveBy(x, y);
    }

    @Override
    public Rectangle displayBox() {
        System.out.println("fDisplayBox.x 3  " + fDisplayBox.x);
        System.out.println("fDisplayBox.y " + fDisplayBox.y);
        return new Rectangle(
                fDisplayBox.x,
                fDisplayBox.y,
                fDisplayBox.width,
                fDisplayBox.height);
    }

    @Override
    public Vector<Handle> handles() {
        Vector<Handle> handles = new Vector<>();
        handles.addElement(new NullHandle(this, RelativeLocator.northWest()));
        handles.addElement(new NullHandle(this, RelativeLocator.northEast()));
        handles.addElement(new NullHandle(this, RelativeLocator.southWest()));
        handles.addElement(new NullHandle(this, RelativeLocator.southEast()));
        handles.addElement(new ConnectionHandle(this, RelativeLocator.east(),
                new GravityConnection())
        );
        return handles;
    }
}
