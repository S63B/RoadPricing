package Project.Pdf;

import Project.Domain.Invoice;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import static Project.Pdf.PdfFonts.catFont;
import static Project.Pdf.PdfFonts.smallBold;
import static Project.Pdf.PdfFonts.subFont;

/**
 * Created by Nekkyou on 4-4-2017.
 */
public class PdfGenerator {
	private Document document;
	private String filename = "factuur";
	private String author = "Overheid";
	private String creator = "FBI";
	private String title = "Title of document";
	private String subject = "Set the subject here";


	public void GenerateInvoicePdf(Invoice invoice) {
		filename = "factuur" + invoice.getUser().getCarTracker();

		document = new Document();
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream( filename + ".pdf"));
			document.open();
			setMetadata();

			createTitlePage();
			addContent();

			document.close();
			writer.close();
		} catch (DocumentException e)
		{
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void createTitlePage() throws DocumentException, IOException {
		document.add(new Paragraph(title, catFont));
		document.add(new Paragraph("Created on: " + new Date().toString(), smallBold));

		createUserInfo();

//		Image img = Image.getInstance(imagePath);
//		img.scaleAbsolute(400f, 266f);
//		document.add(img);

		document.newPage();
	}

	private void createUserInfo() throws DocumentException {
		Paragraph userInfo = new Paragraph("User info", smallBold);
		userInfo.setSpacingBefore(30f);
		document.add(userInfo);

		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.setWidthPercentage(100);
		table.addCell(new Phrase("Cartracker"));
		table.addCell(new Phrase("123"));

		table.addCell(new Phrase("Hardware"));
		table.addCell(new Phrase("1236879"));

		table.addCell(new Phrase("identifaction Method"));
		table.addCell(new Phrase("GPS"));

		table.addCell(new Phrase("Car Category"));
		table.addCell(new Phrase("Old-timer"));
		document.add(table);
	}

	private void addContent() throws DocumentException {
		document.add(new Paragraph("Ritten", subFont));
		document.add(createList());

	}

	private List createList() {
		List unorderedList = new List(List.UNORDERED);
		for (int i = 0; i < 10; i++) {
			unorderedList.add(new ListItem("Item " + i));
		}
		return unorderedList;
	}

	private void setMetadata() {
		//Attributes
		document.addAuthor(author);
		document.addCreationDate();
		document.addCreator(creator);
		document.addTitle(title);
		document.addSubject(subject);
	}
}
