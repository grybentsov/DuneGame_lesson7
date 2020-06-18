package com.dune.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.dune.game.core.Assets;

public class MenuScreen extends AbstractScreen {

    private Stage stage;

    public MenuScreen(SpriteBatch batch) {
        super(batch);
    }

    // Метод создания кнопок на экране меню
    public void createButtons(){
        stage = new Stage(ScreenManager.getInstance().getViewport(), ScreenManager.getInstance().getBatch());
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());
        BitmapFont font14 = Assets.getInstance().getAssetManager().get("fonts/font14.ttf");
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(
                skin.getDrawable("smButton"), null, null, font14);
        final TextButton startGameBtn = new TextButton("Start the game", textButtonStyle);
        startGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAME);
            }
        });
        final TextButton endGameBtn = new TextButton("End the game", textButtonStyle);
        endGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        Group menuGroup = new Group();
        startGameBtn.setPosition(0, 0);
        endGameBtn.setPosition(150, 0);
        menuGroup.addActor(startGameBtn);
        menuGroup.addActor(endGameBtn);
        menuGroup.setPosition(500, 340);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font14, Color.WHITE);
        skin.add("simpleLabel", labelStyle);

        stage.addActor(menuGroup);
        skin.dispose();
    }

    @Override
    public void show() {
        createButtons();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    public void update(float dt) {
        if (Gdx.input.justTouched()) {
            stage.act(dt);
        }
    }

    @Override
    public void dispose() {
    }
}