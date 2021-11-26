package projet;

import java.awt.BorderLayout;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.*;

public class DragAndDropFrame extends JFrame implements DropTargetListener {

	private JFormattedTextField field;
	
	public DragAndDropFrame() {
		// Set the frame title
		super("Drag and drop test");

		// Set the size
		this.setSize(500, 150);

		JLabel myLabel = new JLabel("Enter number of thread below, then drag and drop the file here ", SwingConstants.CENTER);
		field = new JFormattedTextField() ;
		
		// Connect the label with a drag and drop listener
		new DropTarget(myLabel, this);

		// Add the label to the content
		this.getContentPane().add(BorderLayout.CENTER, myLabel);
		this.getContentPane().add(BorderLayout.SOUTH, field);

		// Show the frame
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void drop(DropTargetDropEvent event) {

		// Accept copy drops
		event.acceptDrop(DnDConstants.ACTION_COPY);

		// Get the transfer which can provide the dropped item data
		Transferable transferable = event.getTransferable();

		// Get the data formats of the dropped item
		DataFlavor[] flavors = transferable.getTransferDataFlavors();
		
		int nbOfThreads = Integer.parseInt(field.getText());

		List<File> files;
		try {
			files = (List) transferable.getTransferData(flavors[0]);
			System.out.println("File path is '" + files.get(0).getPath() + "'.");
			if (files.get(0).getPath().endsWith(".txt")) {
				String path = files.get(0).getPath();
				String fileName = path.substring(0, path.length() - 4);
				new Launcher(fileName, nbOfThreads);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Le fichier n'est pas un text", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (UnsupportedFlavorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		event.dropComplete(true);

	}


	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub

	}

}
