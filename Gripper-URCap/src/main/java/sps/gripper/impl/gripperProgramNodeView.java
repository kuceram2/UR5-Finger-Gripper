package sps.gripper.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

import java.awt.Font;

public class gripperProgramNodeView implements SwingProgramNodeView<gripperProgramNodeContribution>{
	
	private final ViewAPIProvider apiProvider;
	
	public gripperProgramNodeView(ViewAPIProvider apiProvider) {
		this.apiProvider = apiProvider;
	}

	private JButton TestButton = new JButton();
	private JButton OpenButton = new JButton();
	private JButton CloseButton = new JButton();
	private JSlider SpeedSlider = new JSlider();
	private JSlider WidthSlider = new JSlider();
	private JSlider StepSlider = new JSlider();
	public JLabel stateLabel = new JLabel("420");
	@Override
	public void buildUI(JPanel panel, ContributionProvider<gripperProgramNodeContribution> provider) {
		// TODO Auto-generated method stub
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(stateLabel);
		panel.add(createDescription("Šířka:"));
		panel.add(createWidthSlider(WidthSlider, 0, 150,"[mm]",provider));
		panel.add(createSpacer(10));
		panel.add(createDescription("Rychlost (relativní):"));
		panel.add(createSpeedSlider(SpeedSlider, 0, 100,"[%]", provider));
		panel.add(createSpacer(10));
		panel.add(createTestButton(TestButton, provider));
		panel.add(createSpacer(50));
		panel.add(createDescription("Ruční ovládání:"));
		panel.add(createStepSlider(StepSlider, 1, 50, "[mm]", provider));
		panel.add(createManualButtons(OpenButton, CloseButton, provider));
		panel.add(createDescription("Nezapomeňte změnit payload!"));
	}
	
		public void updateStateLabel(int newValue) {
			stateLabel.setFont(new Font("Arial", Font.PLAIN, 30));
			stateLabel.setText(Integer.toString(newValue));
			
		}
		
		private Box createDescription(String desc) {
			Box box = Box.createHorizontalBox();
			box.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			JLabel label = new JLabel(desc);
			box.add(label);
			
			return box;
		}
		
		private Box createTestButton(final JButton button, final ContributionProvider <gripperProgramNodeContribution> provider) {
			Box box = Box.createHorizontalBox();
			box.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			button.setPreferredSize(new Dimension(100,50));
			button.setMaximumSize(button.getPreferredSize());
			button.setText("Test");
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					System.out.println("Button clicked!");
					provider.get().onTestButton();
				}
			});
			
			box.add(button);
			
			 return box;
		}
		
		private Box createManualButtons(final JButton openButton, final JButton closeButton,
				final ContributionProvider <gripperProgramNodeContribution> provider) {
			Box box = Box.createHorizontalBox();
			box.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			openButton.setPreferredSize(new Dimension(100,50));
			openButton.setMaximumSize(openButton.getPreferredSize());
			openButton.setText("Otevřít");
			
			openButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					System.out.println("Manual open button");
					provider.get().onOpenButton();
					stateLabel.setText("Aktuální stav: " + provider.get().getSteps());
				}
			});
			
			closeButton.setPreferredSize(new Dimension(100,50));
			closeButton.setMaximumSize(closeButton.getPreferredSize());
			closeButton.setText("Zavřít");
			
			closeButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					System.out.println("Manual close button");
					provider.get().onCloseButton();
					stateLabel.setText("Aktuální stav: " + provider.get().getSteps());
				}
			});
			
			box.add(closeButton);
			Box.createRigidArea(new Dimension(20,0));
			box.add(openButton);
			
			return box;
			
		}
		
		private Component createSpacer(int height) {
			return Box.createRigidArea(new Dimension(0, height));
		}
		
		private Box createWidthSlider(final JSlider slider, int min, int max, String unit,
				final ContributionProvider <gripperProgramNodeContribution> provider) {
			Box box = Box.createHorizontalBox();
			box.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			slider.setMinimum(min);
			slider.setMaximum(max);
			slider.setOrientation(JSlider.HORIZONTAL);
			
			JLabel value = new JLabel(Integer.toString(slider.getValue()));
			
			JLabel label = new JLabel(unit);
			
			slider.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					int newValue = slider.getValue();
					value.setText(Integer.toString(newValue));
					provider.get().onWidthSelection(newValue);
					
				}
			});
			
			box.add(slider);
			box.add(value);
			box.add(label);
			
			return box;
			
		}
		
		private Box createSpeedSlider(final JSlider slider, int min, int max, String unit,
				final ContributionProvider <gripperProgramNodeContribution> provider) {
			Box box = Box.createHorizontalBox();
			box.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			slider.setMinimum(min);
			slider.setMaximum(max);
			slider.setOrientation(JSlider.HORIZONTAL);
			
			JLabel value = new JLabel(Integer.toString(slider.getValue()));
			
			JLabel label = new JLabel(unit);
			
			slider.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					int newValue = slider.getValue();
					value.setText(Integer.toString(newValue));
					provider.get().onSpeedSelection(newValue);
					
				}
			});
			
			box.add(slider);
			box.add(value);
			box.add(label);
			
			return box;
			
		}
	
		private Box createStepSlider(final JSlider slider, int min, int max, String unit,
				final ContributionProvider <gripperProgramNodeContribution> provider) {
			Box box = Box.createHorizontalBox();
			box.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			slider.setMinimum(min);
			slider.setMaximum(max);
			slider.setOrientation(JSlider.HORIZONTAL);
			
			JLabel value = new JLabel(Integer.toString(slider.getValue()));
			
			JLabel label = new JLabel(unit);
			JLabel description = new JLabel("Velikost kroku: ");
			
			slider.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					int newValue = slider.getValue();
					value.setText(Integer.toString(newValue));
					provider.get().onSpeedSelection(newValue);
					
				}
			});
			
			box.add(description);
			box.add(slider);
			box.add(value);
			box.add(label);
			
			return box;
			
		}
		
		public void setWidthSliderValue(int value) {
			WidthSlider.setValue(value);
		}
		
		public void setSpeedSliderValue(int value) {
			SpeedSlider.setValue(value);
		}
}
	