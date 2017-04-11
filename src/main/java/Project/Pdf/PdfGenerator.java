package Project.Pdf;

import Project.Domain.Invoice;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

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
	private String title = "Factuur voor ";
	private String subject = "Set the subject here";
	private LocalDate vandatum;
	private LocalDate totdatum;
	private String land = "Nederland";
	private String username = "Tim Daniëls";
	private double prijs = 50.12;
	private String imagePath = "vlag.png";

	private float imgWidth = 100f;
	private float imgHeight = 66f;

	public void GenerateInvoicePdf() {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(
				FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

		vandatum = LocalDate.parse("01.01.2017", formatter);
		totdatum = LocalDate.parse("31.01.2017", formatter);

		title += username;

		document = new Document();
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream( filename + ".pdf"));
			document.open();
			setMetadata();

			createTitlePage();
			//addContent();

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
		setPdfHeader();
		document.add(new Paragraph(title, catFont));
		document.add(new Paragraph("Created on: " + new Date().toString(), smallBold));

		document.add(new Paragraph("Van: " + vandatum.toString(), smallBold));
		document.add(new Paragraph("Tot: " + totdatum.toString(), smallBold));
		createUserInfo();

		addPricing();

		LineSeparator ls = new LineSeparator();
		Paragraph p = new Paragraph();
		p.add(new Chunk(ls));
		p.setSpacingAfter(30f);

		document.add(p);

		Image img = Image.getInstance(imagePath);
		img.scaleAbsolute(imgWidth, imgHeight);
		img.setAbsolutePosition(PageSize.A4.getWidth() - (imgWidth + 10), PageSize.A4.getHeight() - (imgHeight + 10));
		document.add(img);
	}

	private void setPdfHeader() {

	}

	private void addPricing() throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.setWidthPercentage(100);
		table.addCell(new Phrase("Totaalprijs"));
		table.addCell(new Phrase(String.valueOf(prijs)));
		table.setSpacingBefore(30f);
		document.add(table);
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

		LineSeparator ls = new LineSeparator();
		document.add(new Chunk(ls));
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
