package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimatedImage {
	private static int COLUMNS;
	private static int COUNT;
	private static int OFFSET_X;
	private static int OFFSET_Y;
	private static int WIDTH;
	private static int HEIGHT;
	private SpriteAnimation animation ;
	private ImageView imageView;
	
	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public AnimatedImage(Image image, int COLUMNS, int COUNT, int OFFSET_X, int OFFSET_Y, int WIDTH, int HEIGHT) {
		
		this.COLUMNS = COLUMNS;
		this.COUNT = COUNT;
		this.OFFSET_X = OFFSET_X;
		this.OFFSET_Y = OFFSET_Y;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		imageView = new ImageView(image);
		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

		animation = new SpriteAnimation(imageView, Duration.millis(500), COUNT, COLUMNS, OFFSET_X,
				0, WIDTH, HEIGHT);
		animation.setCycleCount(50);

	}
	
	public void setOffsetY(int OFFSET_Y) {
		animation.setOffsetY(OFFSET_Y);
	}
	
	public void play() {
		animation.play();
	}
	
	public void stop() {
		animation.stop();
	}
}