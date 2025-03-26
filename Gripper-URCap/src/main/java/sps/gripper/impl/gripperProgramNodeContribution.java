package sps.gripper.impl;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoRedoManager;
import com.ur.urcap.api.domain.undoredo.UndoableChanges;
import java.io.PrintWriter;
import java.net.Socket;
//import javax.swing.Timer;
//import java.util.Timer;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gripperProgramNodeContribution implements ProgramNodeContribution{
	
	private final ProgramAPIProvider apiProvider;
	private final gripperProgramNodeView view;
	private final DataModel model;
	private final UndoRedoManager undoRedoManager;
	private Timer labelUpdateTimer;
	
	private static final String WIDTH_KEY = "width";
	private static final String SPEED_KEY = "speed";
	private static final String STEPS_KEY = "steps";
	
	private static final Integer DEFAULT_WIDTH = 0;
	private static final Integer DEFAULT_SPEED = 50;
	private static final Integer DEFAULT_STEPS = 69;
	
	

	public gripperProgramNodeContribution(ProgramAPIProvider apiProvider, gripperProgramNodeView view, DataModel model) {
		this.apiProvider = apiProvider;
		this.view = view;
		this.model = model;
		this.undoRedoManager = this.apiProvider.getProgramAPI().getUndoRedoManager();
		
		
		if(!model.isSet(STEPS_KEY)) {
			model.set(STEPS_KEY, DEFAULT_STEPS);
		}
		System.out.println("steps" + model.get(STEPS_KEY, DEFAULT_STEPS));
		
		labelUpdateTimer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateLabelFromDataModel();
				
				System.out.println(model.get(STEPS_KEY, DEFAULT_STEPS));

			}
		});
		
		
	}
	
	 private void updateLabelFromDataModel() {
	        // Retrieve the value from the DataModel
	        int variableValue = model.get(STEPS_KEY,DEFAULT_STEPS);
	        System.out.print("value updated");
	        // Update the label in the view
	        view.updateStateLabel(variableValue);
	    }
	
	public void onSpeedSelection(final int speed) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				model.set(SPEED_KEY, speed);
				System.out.println(model.get(SPEED_KEY, DEFAULT_SPEED));
				
			}
		});
	}
	
	public void onWidthSelection(final int width) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				model.set(WIDTH_KEY, width);
				
			}
		});
	}         
	
	public void onTestButton() {
		try (Socket socket = new Socket("127.0.1.1", 30002);
	             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

			int width = model.get(WIDTH_KEY, DEFAULT_WIDTH);
			//int steps = model.get(STEPS_KEY, DEFAULT_STEPS);
			int speed = model.get(SPEED_KEY, DEFAULT_SPEED);
			
			int n = 1; // převodní poměr mm <-> steps
			int rychlost = (100 - speed) / 100 + 1;
			double f = 0.05 * rychlost;
			//int diff = width - steps;
			//int diffSteps = diff * n;
			//out.println("if True: set_digital_out(2, True)\n" +
			// "else: set_digital_out(3, True)\n" +
			 //"end");
			out.println("set_digital_out(1,True)");
			Thread.sleep(300);
			out.println("set_digital_out(4,True)");
			//out.print("set_digital_out(1,True)"
			//		+ "width_ = " + width
			//		+ "diff = width_ - i_steps "
			//		+ "pulses = diff + " + n
			//		+ "period = " + f
			//		+ "if pulses > 0: set_digital_out(8, True)"
			//		+ "else: set_digital_out(8,False) end"
			//		+ "i = 0"
			//		+ "while norm(pulses) > i:"
			//		+ "set_digital_out(9,True)"
			//		+ "sleep(period)" 
			//		+ "set_digital_out(9,False)"
			//		+ "sleep(period)"
			//		+ "i = i + 1"
			//		+ "end"
			//		+ "i_steps = 0"
			//		+ "i_steps =" + width);
			//out.println("set_digital_out(1,True)");
           // out.println("width_ = " + width);
			//out.println("diff = width_ - i_steps ");
			//out.println("pulses = diff + " + n);
		//	out.println("period = " + f);
			//out.println("if pulses > 0: set_digital_out(8, True)");
			//out.println("else: set_digital_out(8,False) end");
			//out.println("i = 0");
			////out.println("while norm(pulses) > i:");
			//out.println("set_digital_out(9,True)");
			//out.println("sleep(period)");
			//out.println("set_digital_out(9,False)");
			//out.println("sleep(period)");
			//out.println("i = i + 1");
			//out.println("end");
			//out.println("i_steps = 0");
			//out.println("i_steps =" + width);
			view.updateStateLabel(width);
            System.out.println("Sent URScript: ");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            System.out.println("Failed to send script to robot: " + ex.getMessage());
	        }
	    }
	
	public void onOpenButton() {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				int newSteps = model.get(STEPS_KEY, DEFAULT_STEPS);
				newSteps = newSteps + 5;
				model.set(STEPS_KEY, newSteps);
				System.out.println(model.get(STEPS_KEY, DEFAULT_STEPS));				
			}
		});
		
	}
	
	public void onCloseButton() {
undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				int newSteps = model.get(STEPS_KEY, DEFAULT_STEPS);
				newSteps = newSteps - 5;
				model.set(STEPS_KEY, newSteps);
				System.out.println(model.get(STEPS_KEY, DEFAULT_STEPS));
				
			}
		});
	}
	
	private Integer getWidth() {
		return model.get(WIDTH_KEY, DEFAULT_WIDTH);
	}
	
	private Integer getSpeed() {
		return model.get(SPEED_KEY, DEFAULT_SPEED);
	}
	
	public Integer getSteps() {
		return model.get(STEPS_KEY, DEFAULT_STEPS);
	}
	
	@Override
	public void openView() {
		// TODO Auto-generated method stub
		view.setWidthSliderValue(getWidth());
		view.setSpeedSliderValue(getSpeed());
		System.out.println("před nastavenim: " + model.get(STEPS_KEY, DEFAULT_STEPS));
		System.out.println("to je ono: " + (view.stateLabel.getText()));
		model.set(STEPS_KEY, view.stateLabel.getText());
		System.out.println("po nastavenim: " + model.get(STEPS_KEY, DEFAULT_STEPS));
		view.updateStateLabel(model.get(STEPS_KEY, DEFAULT_STEPS));		
	}


	@Override
	public void closeView() {
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Gripper";
	}

	@Override
	public boolean isDefined() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		int width = model.get(WIDTH_KEY, DEFAULT_WIDTH);
		System.out.println(width);
		//int steps = model.get(STEPS_KEY, DEFAULT_STEPS);
		int speed = model.get(SPEED_KEY, DEFAULT_SPEED);
		
		int n = 1; // převodní poměr mm <-> steps
		int rychlost = (100 - speed) / 100 + 1;
		int f = 1 * rychlost;
		//int diff = width - steps;
		//int diffSteps = diff * n;
		writer.appendLine("width_ = " + width);
		writer.appendLine("diff = width_ - i_steps ");
		writer.appendLine("pulses = diff + " + n);
		writer.appendLine("period = " + f);
		writer.appendLine("if pulses > 0: set_digital_out(8, True)");
		writer.appendLine("else: set_digital_out(8,False) end");
		writer.appendLine("i = 0");
		writer.appendLine("while norm(pulses) >= i:");
		writer.appendLine("set_digital_out(9,True)");
		writer.appendLine("sleep(period)");
		writer.appendLine("set_digital_out(9,False)");
		writer.appendLine("sleep(period)");
		writer.appendLine("i = i + 1");
		writer.appendLine("end");
		//writer.appendLine("i_steps = 0");
		writer.appendLine("i_steps =" + width);
		view.updateStateLabel(width);
		System.out.println("value updated");
	}
	

}
