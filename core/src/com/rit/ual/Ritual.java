package com.rit.ual;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Ritual extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private PerspectiveCamera cam;
    private Model model;
    private ModelInstance instance;
    private ModelBatch modelBatch;

    @Override
	public void create () {
		batch = new SpriteBatch();
        modelBatch = new ModelBatch();
		img = new Texture("core/assets/badlogic.jpg");

        cam = new PerspectiveCamera(100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0, 0, 500);

        cam.lookAt(0,0,0);
        cam.near = 1;
        cam.far = 1000;
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(50f, 50f, 50f,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);


        instance = new ModelInstance(model);
        instance.tra

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render () {
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.position.z += 1f;
        } else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.position.z -= 1f;
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.rotateAround(new Vector3(0, 0, 0), new Vector3(1, 0, 0), 1);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.rotateAround(new Vector3(0, 0, 0), new Vector3(1, 0, 0), -1);
        }

        cam.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.begin();
        batch.setProjectionMatrix(cam.combined);
		batch.draw(img, 0, 0);
		batch.end();

        modelBatch.begin(cam);
        modelBatch.render(instance);


        modelBatch.end();


	}
}
