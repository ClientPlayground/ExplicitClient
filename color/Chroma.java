package me.explicit.color;

import java.awt.Color;
import me.explicit.utils.TimerUtils;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Chroma {

    private TimerUtils timer = new TimerUtils();
    private int lastHue;

    @SubscribeEvent
    public void render(RenderGameOverlayEvent rendergameoverlayevent) {
        if (rendergameoverlayevent.type.equals(ElementType.TEXT) && this.timer.hasReached(33.3333333333D)) {
            this.changeColor();
            this.timer.reset();
        }

    }

    public Color getColor(int i) {
        int j;

        for (j = this.lastHue + i * 2; j > 1000; j -= 1000) {
            ;
        }

        return Color.getHSBColor((float) j / 1000.0F, 1.0F, 1.0F);
    }

    private void changeColor() {
        this.lastHue += 5;
        if (this.lastHue > 1000) {
            this.lastHue = 0;
        }

    }
}
