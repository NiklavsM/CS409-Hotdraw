/*
 * @(#)ElbowConnection.java 5.1
 *
 */

package CH.ifa.draw.figure.connection;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.framework.Handle;
import CH.ifa.draw.framework.Locator;
import CH.ifa.draw.handle.ChangeConnectionEndHandle;
import CH.ifa.draw.handle.ChangeConnectionStartHandle;
import CH.ifa.draw.handle.ElbowHandle;
import CH.ifa.draw.handle.NullHandle;
import CH.ifa.draw.locator.AbstractLocator;
import CH.ifa.draw.util.Geom;

/**
 * A LineConnection that constrains a connection to
 * orthogonal lines.
 */
public  class ElbowConnection extends LineConnection {

    /*
     * Serialization support.
     */
    private static final long serialVersionUID = 2193968743082078559L;
    @SuppressWarnings("unused")
	private int elbowConnectionSerializedDataVersion = 1;

    public ElbowConnection() {
        super();
    }

    @Override
	public Locator connectedTextLocator(Figure f) {
        return new ElbowTextLocator();
    }

    /**
     * Gets the handles of the figure.
     */
    @Override
	public Vector<Handle> handles() {
        Vector<Handle> handles = new Vector<Handle>(fPoints.size()*2);
        handles.addElement(new ChangeConnectionStartHandle(this));
        for (int i = 1; i < fPoints.size()-1; i++)
            handles.addElement(new NullHandle(this, locator(i)));
        handles.addElement(new ChangeConnectionEndHandle(this));
        for (int i = 0; i < fPoints.size()-1; i++)
            handles.addElement(new ElbowHandle(this, i));
        return handles;
    }

    @Override
	public void layoutConnection() {
    }

    @Override
	public void updateConnection() {
        super.updateConnection();
        updatePoints();
    }

    protected void updatePoints() {
        willChange();
        Point start = startPoint();
        Point end = endPoint();
        fPoints.removeAllElements();
        fPoints.addElement(start);

        if (start.x == end.x || start.y == end.y) {
            fPoints.addElement(end);
        }
        else {
            Rectangle r1 = start().owner().displayBox();
            Rectangle r2 = end().owner().displayBox();

            @SuppressWarnings("unused")
			int x1, y1, x2, y2;
            int dir = Geom.direction(r1.x + r1.width/2, r1.y + r1.height/2,
                        r2.x + r2.width/2, r2.y + r2.height/2);
            if (dir == Geom.NORTH || dir == Geom.SOUTH) {
                fPoints.addElement(new Point(start.x, (start.y + end.y)/2));
                fPoints.addElement(new Point(end.x, (start.y + end.y)/2));
            }
            else {
                fPoints.addElement(new Point((start.x + end.x)/2, start.y));
                fPoints.addElement(new Point((start.x + end.x)/2, end.y));
            }
            fPoints.addElement(end);
        }
        changed();
    }
}

class ElbowTextLocator extends AbstractLocator {

	private static final long serialVersionUID = 6441331122823357474L;

	@Override
	public Point locate(Figure owner) {
        Point p = owner.center();
        @SuppressWarnings("unused")
		Rectangle r = owner.displayBox();
        return new Point(p.x, p.y-10); // hack
    }
}

