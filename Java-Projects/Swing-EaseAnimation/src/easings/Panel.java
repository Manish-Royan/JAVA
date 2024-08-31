package easings;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.Interpolator;

public class Panel extends JComponent{
	
        public void setEasein(Easings easing)
        {
            animator.setInterpolator(new Interpolator()
            {
                @Override
                public float interpolate(float f)
                {
                   return easing.easing(f);
                }
            }
            );          
        }
    
	private Animator animator;
	private float animate;
	
	public Panel() {
		TimingTarget target = new TimingTargetAdapter()
		{
			@Override
			public void timingEvent(float fraction)
			{
				animate = fraction;
				repaint();
			}
		};
		
		animator = new Animator(2000, target);
		animator.setResolution(0);
	}
	
	public void start()
	{
		if(!animator.isRunning())
		{
			animator.start();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int size = 50;
		double width = getWidth()-size;
		
		g2D.setColor(getBackground());
		g2D.fillOval((int)(animate*width), 0, size, size);
		super.paint(g);
	}
}