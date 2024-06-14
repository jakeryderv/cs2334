import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;

public class Driver {

    private static ArrayList<TripPoint> tripData;
    private static ArrayList<TripPoint> movingTripData;
    private static JFrame frame;
    private static JMapViewer mapViewer;
    private static JComboBox<Integer> animationTimeComboBox;
    private static JCheckBox includeStopsCheckBox;
    private static JButton playButton;
    private static Timer animationTimer;
    private static int animationStep = 0;
    private static Image raccoonImage;
    private static MapMarker lastMarker = null;
    private static ArrayList<Coordinate> pathCoordinates = new ArrayList<>();

    public static void main(String[] args) {
        try {
            TripPoint.readFile("triplog.csv");
            TripPoint.h1StopDetection();
            tripData = TripPoint.getTrip();
            movingTripData = TripPoint.getMovingTrip();

            System.setProperty("http.agent", "My Map Application");

            SwingUtilities.invokeLater(() -> {
                initializeGUI();
            });
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "I/O Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void initializeGUI() {
        frame = new JFrame("Project 5 - Your Name Here");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel animationTimeLabel = new JLabel("Animation Time:");
        playButton = new JButton("Play");
        playButton.addActionListener(e -> handlePlayButton());
        includeStopsCheckBox = new JCheckBox("Include Stops", false);

        Integer[] animationTimes = {15, 30, 60, 90};
        animationTimeComboBox = new JComboBox<>(animationTimes);

        controlsPanel.add(animationTimeLabel);
        controlsPanel.add(animationTimeComboBox);
        controlsPanel.add(includeStopsCheckBox);
        controlsPanel.add(playButton);

        topPanel.add(controlsPanel, BorderLayout.CENTER);

        mapViewer = new JMapViewer();
        mapViewer.setTileSource(new OsmTileSource.Mapnik());
        raccoonImage = new ImageIcon("raccoon.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        if (!tripData.isEmpty()) {
            TripPoint centerPoint = tripData.get(tripData.size() / 2);
            mapViewer.setDisplayPosition(new Coordinate(centerPoint.getLat(), centerPoint.getLon()), 17);
        }

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mapViewer, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void handlePlayButton() {
        mapViewer.removeAllMapMarkers();
        mapViewer.removeAllMapPolygons();
        ArrayList<TripPoint> activeTripData = includeStopsCheckBox.isSelected() ? tripData : movingTripData;
        int animationTime = (Integer) animationTimeComboBox.getSelectedItem();
        int delay = (animationTime * 1000) / activeTripData.size();

        animationStep = 0;
        pathCoordinates.clear();

        animationTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animationStep < activeTripData.size()) {
                    if (lastMarker != null) {
                        mapViewer.removeMapMarker(lastMarker);
                    }
                    TripPoint currentPoint = activeTripData.get(animationStep);
                    Coordinate currentCoordinate = new Coordinate(currentPoint.getLat(), currentPoint.getLon());
                    IconMarker currentMarker = new IconMarker(currentCoordinate, 0.5, raccoonImage);
                    mapViewer.addMapMarker(currentMarker);
                    lastMarker = currentMarker;

                    if (!pathCoordinates.isEmpty()) {
                        Coordinate lastCoordinate = pathCoordinates.get(pathCoordinates.size() - 1);
                        ArrayList<Coordinate> linePoints = new ArrayList<>();
                        linePoints.add(lastCoordinate);
                        linePoints.add(currentCoordinate);
                        MapPolygon polygon = new MapPolygonImpl(linePoints);
                        mapViewer.addMapPolygon(polygon);
                    }

                    pathCoordinates.add(currentCoordinate);
                    mapViewer.setDisplayPosition(currentCoordinate, mapViewer.getZoom());

                    animationStep++;
                    if (animationStep >= activeTripData.size()) {
                        animationTimer.stop();
                    }
                }
            }
        });
        animationTimer.setInitialDelay(0);
        animationTimer.start();
    }
}
