package se.iths.javatwentytwo.labthree.labthree.model;

import org.junit.jupiter.api.Test;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.ShapeType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArtistModelTest {

    @Test
    void undoWhenCreatingNewShapeToShapeList() {

        ArtistModel artistModel = new ArtistModel();
        artistModel.createShapeToList(ShapeType.CIRCLE);
        artistModel.undoLastCommand();

        var expected = 0;
        var actual = artistModel.shapeList.size();

        assertEquals(expected, actual);
    }

    @Test
    void redoWhenRemoveLastShapeInShapeList() {

        ArtistModel artistModel = new ArtistModel();

        artistModel.createShapeToList(ShapeType.CIRCLE);
        artistModel.createShapeToList(ShapeType.RECT);
        artistModel.shapeList.remove(artistModel.shapeList.size() - 1);
        artistModel.redoLastCommand();

        var expected = 1;
        var actual = artistModel.shapeList.size();

        assertEquals(expected, actual);

    }

    @Test
    void incomingSvgAddToShapeList(){

        ArtistModel artistModel = new ArtistModel();
        String line = "<rect x=\"35.23\" y=\"68.936\" width=\"200.34\" height=\"200.23\" fill=\"#8066cc\" />";
        artistModel.addSvgToShapeList(line);

        var expected = 1;
        var actual = artistModel.shapeList.size();

        assertEquals(expected, actual);
    }

}