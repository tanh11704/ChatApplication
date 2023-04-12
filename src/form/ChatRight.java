package form;

import java.awt.Color;

import javax.swing.JPanel;

public class ChatRight extends JPanel {
	
	private ChatPanel txt;

	/**
	 * Create the panel.
	 */
	public ChatRight() {
		txt = new ChatPanel();
		txt.setBackground(new Color(163, 110, 229));
		add(txt);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
		
	}
	
	public void setText(String text) {
        txt.setText(text);
    }

}
