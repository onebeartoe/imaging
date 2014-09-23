
package org.onebeartoe.imaging.gifs;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import org.onebeartoe.ui.listener.AsynchIntermediateProgressUpdatingWorkerListener;

/**
 * @author Roberto Marquez
 */
public class GifAnimationListener extends AsynchIntermediateProgressUpdatingWorkerListener
{
    JFileChooser inputChooser;
    
    public GifAnimationListener(Component parent, JProgressBar progressBar, JTextArea outputArea, JFileChooser inputChooser) 
    {
        super(parent, progressBar, outputArea);
        
        this.inputChooser = inputChooser;
    }
 
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );

        GifAnimationWorker worker = new GifAnimationWorker(parent, outputArea, inputChooser);
        worker.addPropertyChangeListener( new ProgressChangedListener() );
        worker.execute();
    }
    
    private class ProgressChangedListener implements PropertyChangeListener
    {
        public  void propertyChange(PropertyChangeEvent evt) 
        {
            if ("progress".equals(evt.getPropertyName())) 
            {
                progressBar.setValue((Integer)evt.getNewValue());
            }
        }
    }
    
}
