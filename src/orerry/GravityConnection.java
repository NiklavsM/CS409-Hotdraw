package orerry;

import CH.ifa.draw.figure.ArrowTip;
import CH.ifa.draw.figure.PolyLineFigure;
import CH.ifa.draw.figure.connection.LineConnection;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.framework.Handle;
import CH.ifa.draw.handle.NullHandle;

import java.util.Vector;

public class GravityConnection extends LineConnection {
    public GravityConnection() {
        super();
        setEndDecoration(new ArrowTip());
        setStartDecoration(null);
    }

    @Override
    public boolean canConnect(Figure start, Figure end) {
        return ((start instanceof PlanetFigure || start instanceof SatelliteFigure) && end instanceof PlanetFigure);
    }


    @Override
    public Vector<Handle> handles() {
        Vector<Handle> handles = super.handles();
        // don't allow to reconnect the starting figure
        handles.setElementAt(
                new NullHandle(this, PolyLineFigure.locator(0)), 0);
        return handles;
    }
}
