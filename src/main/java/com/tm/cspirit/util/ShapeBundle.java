package com.tm.cspirit.util;

import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ShapeBundle {

    private final List<VoxelShape> shapes;
    private VoxelShape combinedShape;

    public ShapeBundle() {
        shapes = new ArrayList<>();
    }

    public ShapeBundle addShape(VoxelShape shape) {
        shapes.add(shape);
        setVoxelShape();
        return this;
    }

    private VoxelShape[] getArray() {

        VoxelShape[] array = new VoxelShape[shapes.size()];

        for (int i = 0; i < array.length; i++) {
            array[i] = shapes.get(i);
        }

        return array;
    }

    private void setVoxelShape() {
        combinedShape = Stream.of(getArray()).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2 , IBooleanFunction.OR)).get();
    }

    public VoxelShape getCombinedShape() {
        return combinedShape;
    }
}
