package Score_Manager_Package;

import java.awt.BasicStroke;

import java.awt.Color;

import java.awt.Font;

import java.awt.GradientPaint;

import java.awt.Paint;

import org.jfree.chart.ChartFrame;

import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.CategoryAxis;

import org.jfree.chart.axis.CategoryLabelPositions;

import org.jfree.chart.axis.NumberAxis;

import org.jfree.chart.labels.CategoryItemLabelGenerator;

import org.jfree.chart.labels.ItemLabelAnchor;

import org.jfree.chart.labels.ItemLabelPosition;

import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;

import org.jfree.chart.plot.CategoryPlot;

import org.jfree.chart.plot.DatasetRenderingOrder;

import org.jfree.chart.plot.PlotOrientation;

import org.jfree.chart.renderer.category.BarRenderer;

import org.jfree.chart.renderer.category.CategoryItemRenderer;

import org.jfree.chart.renderer.category.LineAndShapeRenderer;

import org.jfree.chart.renderer.category.StandardBarPainter;

import org.jfree.chart.title.TextTitle;

import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.ui.GradientPaintTransformType;

import org.jfree.ui.HorizontalAlignment;

import org.jfree.ui.StandardGradientPaintTransformer;

import org.jfree.ui.TextAnchor;

/**
 * 
 * A simple demonstration application showing how to create a bar chart overlaid
 * 
 * with a line chart.
 * 
 */

public class TCChartSelect {

	// Run As > Java Application ���� �����ϸ� �ٷ� Ȯ���� �� ����.

	public static void main(final String[] args) {

		TCChartSelect demo = new TCChartSelect();

		JFreeChart chart = demo.getChart();

		ChartFrame frame1 = new ChartFrame("������ȭ ����", chart);
		
		frame1.setBounds(240, 200, 500, 380);

		frame1.setSize(540, 350);

//		frame1.setSize(800, 400);

		frame1.setVisible(true);

	}

	public JFreeChart getChart() {

		// ������ ����

		DefaultCategoryDataset datasetKOR = new DefaultCategoryDataset(); // line chart 1

		DefaultCategoryDataset datasetENG = new DefaultCategoryDataset();

		DefaultCategoryDataset datasetMATH = new DefaultCategoryDataset();

		DefaultCategoryDataset datasetSOCIETY = new DefaultCategoryDataset();

		DefaultCategoryDataset datasetSCIENCE = new DefaultCategoryDataset();

		//�α����� �л��� ���� ���� �ҷ�����
		Controller controll = new Controller();
		
		
		// ������ �Է� ( ��, ����, ī�װ� )

		// �׷��� ����

		datasetKOR.addValue(controll.getChartScore_KOR_1m(), "����", "1�б� �߰�");

		datasetKOR.addValue(controll.getChartScore_KOR_1e(), "����", "1�б� �⸻");

		datasetKOR.addValue(controll.getChartScore_KOR_2m(), "����", "2�б� �߰�");

		datasetKOR.addValue(controll.getChartScore_KOR_2e(), "����", "2�б� �⸻");
		// �׷��� ����

		datasetENG.addValue(controll.getChartScore_ENG_1m(), "����", "1�б� �߰�");

		datasetENG.addValue(controll.getChartScore_ENG_1e(), "����", "1�б� �⸻");

		datasetENG.addValue(controll.getChartScore_ENG_2m(), "����", "2�б� �߰�");

		datasetENG.addValue(controll.getChartScore_ENG_2e(), "����", "2�б� �⸻");

		// �׷��� ����

		datasetMATH.addValue(controll.getChartScore_MATH_1m(), "����", "1�б� �߰�");

		datasetMATH.addValue(controll.getChartScore_MATH_1e(), "����", "1�б� �⸻");

		datasetMATH.addValue(controll.getChartScore_MATH_2m(), "����", "2�б� �߰�");

		datasetMATH.addValue(controll.getChartScore_MATH_2e(), "����", "2�б� �⸻");

		// �׷��� ��ȸ

		datasetSOCIETY.addValue(controll.getChartScore_SOCIETY_1m(), "��ȸ", "1�б� �߰�");

		datasetSOCIETY.addValue(controll.getChartScore_SOCIETY_1e(), "��ȸ", "1�б� �⸻");

		datasetSOCIETY.addValue(controll.getChartScore_SOCIETY_2m(), "��ȸ", "2�б� �߰�");

		datasetSOCIETY.addValue(controll.getChartScore_SOCIETY_2e(), "��ȸ", "2�б� �⸻");

		// �׷��� ����

		datasetSCIENCE.addValue(controll.getChartScore_SCIENCE_1m(), "����", "1�б� �߰�");

		datasetSCIENCE.addValue(controll.getChartScore_SCIENCE_1e(), "����", "1�б� �⸻");

		datasetSCIENCE.addValue(controll.getChartScore_SCIENCE_2m(), "����", "2�б� �߰�");

		datasetSCIENCE.addValue(controll.getChartScore_SCIENCE_2e(), "����", "2�б� �⸻");

		// ������ ���� �� ����

		// ������ ����

		final LineAndShapeRenderer rendererKOR = new LineAndShapeRenderer();

		final LineAndShapeRenderer rendererENG = new LineAndShapeRenderer();

		final LineAndShapeRenderer rendererMATH = new LineAndShapeRenderer();

		final LineAndShapeRenderer rendererSOCIETY = new LineAndShapeRenderer();

		final LineAndShapeRenderer rendererSCIENCE = new LineAndShapeRenderer();

		// ���� �ɼ� ����

		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();

		final ItemLabelPosition p_center = new ItemLabelPosition(

				ItemLabelAnchor.CENTER, TextAnchor.CENTER

		);

		final ItemLabelPosition p_below = new ItemLabelPosition(

				ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT

		);

		Font f = new Font("Gulim", Font.BOLD, 14);

		Font axisF = new Font("Gulim", Font.PLAIN, 14);

		// ������ ����

		// �׷��� ����

		rendererKOR.setBaseItemLabelGenerator(generator);

		rendererKOR.setBaseItemLabelsVisible(true);

		rendererKOR.setBaseShapesVisible(true);

		rendererKOR.setDrawOutlines(true);

		rendererKOR.setUseFillPaint(true);

		rendererKOR.setBaseFillPaint(Color.WHITE);

		rendererKOR.setBaseItemLabelFont(f);

		rendererKOR.setBasePositiveItemLabelPosition(p_below);

		rendererKOR.setSeriesPaint(0, new Color(34, 121, 22));

		rendererKOR.setSeriesStroke(0, new BasicStroke(

				2.0f,

				BasicStroke.CAP_ROUND,

				BasicStroke.JOIN_ROUND,

				3.0f)

		);

		// �׷��� ����

		rendererENG.setBaseItemLabelGenerator(generator);

		rendererENG.setBaseItemLabelsVisible(true);

		rendererENG.setBaseShapesVisible(true);

		rendererENG.setDrawOutlines(true);

		rendererENG.setUseFillPaint(true);

		rendererENG.setBaseFillPaint(Color.WHITE);

		rendererENG.setBaseItemLabelFont(f);

		rendererENG.setBasePositiveItemLabelPosition(p_below);

		rendererENG.setSeriesPaint(0, new Color(44, 23, 107));

		rendererENG.setSeriesStroke(0, new BasicStroke(

				2.0f,

				BasicStroke.CAP_ROUND,

				BasicStroke.JOIN_ROUND,

				3.0f)

		);

		// �׷��� ����

		rendererMATH.setBaseItemLabelGenerator(generator);

		rendererMATH.setBaseItemLabelsVisible(true);

		rendererMATH.setBaseShapesVisible(true);

		rendererMATH.setDrawOutlines(true);

		rendererMATH.setUseFillPaint(true);

		rendererMATH.setBaseFillPaint(Color.WHITE);

		rendererMATH.setBaseItemLabelFont(f);

		rendererMATH.setBasePositiveItemLabelPosition(p_below);

		rendererMATH.setSeriesPaint(0, new Color(200, 85, 122));

		rendererMATH.setSeriesStroke(0, new BasicStroke(

				2.0f,

				BasicStroke.CAP_ROUND,

				BasicStroke.JOIN_ROUND,

				3.0f)

		);

		// �׷��� ��ȸ

		rendererSOCIETY.setBaseItemLabelGenerator(generator);

		rendererSOCIETY.setBaseItemLabelsVisible(true);

		rendererSOCIETY.setBaseShapesVisible(true);

		rendererSOCIETY.setDrawOutlines(true);

		rendererSOCIETY.setUseFillPaint(true);

		rendererSOCIETY.setBaseFillPaint(Color.WHITE);

		rendererSOCIETY.setBaseItemLabelFont(f);

		rendererSOCIETY.setBasePositiveItemLabelPosition(p_below);

		rendererSOCIETY.setSeriesPaint(0, new Color(234, 150, 180));

		rendererSOCIETY.setSeriesStroke(0, new BasicStroke(

				2.0f,

				BasicStroke.CAP_ROUND,

				BasicStroke.JOIN_ROUND,

				3.0f)

		);

		// �׷��� ����

		rendererSCIENCE.setBaseItemLabelGenerator(generator);

		rendererSCIENCE.setBaseItemLabelsVisible(true);

		rendererSCIENCE.setBaseShapesVisible(true);

		rendererSCIENCE.setDrawOutlines(true);

		rendererSCIENCE.setUseFillPaint(true);

		rendererSCIENCE.setBaseFillPaint(Color.WHITE);

		rendererSCIENCE.setBaseItemLabelFont(f);

		rendererSCIENCE.setBasePositiveItemLabelPosition(p_below);

		rendererSCIENCE.setSeriesPaint(0, new Color(219, 121, 22));

		rendererSCIENCE.setSeriesStroke(0, new BasicStroke(

				2.0f,

				BasicStroke.CAP_ROUND,

				BasicStroke.JOIN_ROUND,

				3.0f)

		);

		// plot ����

		final CategoryPlot plot = new CategoryPlot();

		// plot �� ������ ����

		plot.setDataset(datasetKOR);

		plot.setRenderer(rendererKOR);
		
		plot.setDataset(1, datasetENG);

		plot.setRenderer(1, rendererENG);
		
		plot.setDataset(2, datasetMATH);

		plot.setRenderer(2, rendererMATH);
		
		plot.setDataset(3, datasetSOCIETY);

		plot.setRenderer(3, rendererSOCIETY);
		
		plot.setDataset(4, datasetSCIENCE);

		plot.setRenderer(4, rendererSCIENCE);

		// plot �⺻ ����

		plot.setOrientation(PlotOrientation.VERTICAL); // �׷��� ǥ�� ����

		plot.setRangeGridlinesVisible(true); // X�� ���̵� ���� ǥ�ÿ���

		plot.setDomainGridlinesVisible(true); // Y�� ���̵� ���� ǥ�ÿ���

		// ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		// X�� ����

		plot.setDomainAxis(new CategoryAxis()); // X�� ���� ����

		plot.getDomainAxis().setTickLabelFont(axisF); // X�� ���ݶ� ��Ʈ ����

		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // ī�װ� �� ��ġ ����

		// Y�� ����

		plot.setRangeAxis(new NumberAxis());
		plot.getRangeAxis().setRange(0.0, 100.0);// Y�� ���� ����

		plot.getRangeAxis().setTickLabelFont(axisF); // Y�� ���ݶ� ��Ʈ ����

		// ���õ� plot�� �������� chart ����

		final JFreeChart chart = new JFreeChart(plot);

//        chart.setTitle("Overlaid Bar Chart"); // ��Ʈ Ÿ��Ʋ

//        TextTitle copyright = new TextTitle("JFreeChart WaferMapPlot", new Font("SansSerif", Font.PLAIN, 9));

//        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);

//        chart.addSubtitle(copyright);  // ��Ʈ ���� Ÿ��Ʋ

		return chart;

	}

}

//��ó: https://androphil.tistory.com/441 [�Ҹ����� ȫ����!]
