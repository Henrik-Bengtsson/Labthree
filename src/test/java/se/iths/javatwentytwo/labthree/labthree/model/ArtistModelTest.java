package se.iths.javatwentytwo.labthree.labthree.model;

import org.junit.jupiter.api.Test;
import se.iths.javatwentytwo.labthree.labthree.model.shapes.ShapeType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArtistModelTest {

    @Test
    void undoWhenCreatingNewShapeToShapeList() {

        ArtistModel artistModel = new ArtistModel();

        artistModel.createShapeToList(ShapeType.CIRCLE);
        var expected = artistModel.shapeList.size() - 1;

        artistModel.undoLastCommand();
        var actual = artistModel.shapeList.size();

        assertEquals(expected, actual);
    }

    @Test
    void redoWhenRemoveLastShapeInShapeList() {

        ArtistModel artistModel = new ArtistModel();

        artistModel.createShapeToList(ShapeType.CIRCLE);
        artistModel.createShapeToList(ShapeType.RECT);
        artistModel.shapeList.remove(artistModel.shapeList.size() - 1);
        var expected = artistModel.shapeList.size();

        artistModel.redoLastCommand();
        var actual = artistModel.shapeList.size();

        assertEquals(expected, actual);

    }

}