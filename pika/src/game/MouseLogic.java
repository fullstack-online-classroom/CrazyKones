package game;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class MouseLogic implements MouseHandler {

    private Mouse mouse;
    private Rectangle clickableBox;

    public MouseLogic() {
        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    public void setClickableBox(Rectangle clickableBox) {
        this.clickableBox = clickableBox;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // game logic
        if(mouseEvent.getX() > clickableBox.getX() &&
            mouseEvent.getX() < clickableBox.getX() + clickableBox.getWidth() &&
            mouseEvent.getY() > clickableBox.getY() &&
            mouseEvent.getY() < clickableBox.getY() + clickableBox.getHeight()){
            System.out.println("clicked inside button.....?");
        }

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
