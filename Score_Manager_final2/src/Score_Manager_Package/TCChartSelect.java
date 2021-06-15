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

	// Run As > Java Application 으로 실행하면 바로 확인할 수 있음.

	public static void main(final String[] args) {

		TCChartSelect demo = new TCChartSelect();

		JFreeChart chart = demo.getChart();

		ChartFrame frame1 = new ChartFrame("성적변화 추이", chart);
		
		frame1.setBounds(240, 200, 500, 380);

		frame1.setSize(540, 350);

//		frame1.setSize(800, 400);

		frame1.setVisible(true);

	}

	public JFreeChart getChart() {

		// 데이터 생성

		DefaultCategoryDataset datasetKOR = new DefaultCategoryDataset(); // line chart 1

		DefaultCategoryDataset datasetENG = new DefaultCategoryDataset();

		DefaultCategoryDataset datasetMATH = new DefaultCategoryDataset();

		DefaultCategoryDataset datasetSOCIETY = new DefaultCategoryDataset();

		DefaultCategoryDataset datasetSCIENCE = new DefaultCategoryDataset();

		//로그인한 학생의 성적 정보 불러오기
		Controller controll = new Controller();
		
		
		// 데이터 입력 ( 값, 범례, 카테고리 )

		// 그래프 국어

		datasetKOR.addValue(controll.getChartScore_KOR_1m(), "국어", "1학기 중간");

		datasetKOR.addValue(controll.getChartScore_KOR_1e(), "국어", "1학기 기말");

		datasetKOR.addValue(controll.getChartScore_KOR_2m(), "국어", "2학기 중간");

		datasetKOR.addValue(controll.getChartScore_KOR_2e(), "국어", "2학기 기말");
		// 그래프 영어

		datasetENG.addValue(controll.getChartScore_ENG_1m(), "영어", "1학기 중간");

		datasetENG.addValue(controll.getChartScore_ENG_1e(), "영어", "1학기 기말");

		datasetENG.addValue(controll.getChartScore_ENG_2m(), "영어", "2학기 중간");

		datasetENG.addValue(controll.getChartScore_ENG_2e(), "영어", "2학기 기말");

		// 그래프 수학

		datasetMATH.addValue(controll.getChartScore_MATH_1m(), "수학", "1학기 중간");

		datasetMATH.addValue(controll.getChartScore_MATH_1e(), "수학", "1학기 기말");

		datasetMATH.addValue(controll.getChartScore_MATH_2m(), "수학", "2학기 중간");

		datasetMATH.addValue(controll.getChartScore_MATH_2e(), "수학", "2학기 기말");

		// 그래프 사회

		datasetSOCIETY.addValue(controll.getChartScore_SOCIETY_1m(), "사회", "1학기 중간");

		datasetSOCIETY.addValue(controll.getChartScore_SOCIETY_1e(), "사회", "1학기 기말");

		datasetSOCIETY.addValue(controll.getChartScore_SOCIETY_2m(), "사회", "2학기 중간");

		datasetSOCIETY.addValue(controll.getChartScore_SOCIETY_2e(), "사회", "2학기 기말");

		// 그래프 과학

		datasetSCIENCE.addValue(controll.getChartScore_SCIENCE_1m(), "과학", "1학기 중간");

		datasetSCIENCE.addValue(controll.getChartScore_SCIENCE_1e(), "과학", "1학기 기말");

		datasetSCIENCE.addValue(controll.getChartScore_SCIENCE_2m(), "과학", "2학기 중간");

		datasetSCIENCE.addValue(controll.getChartScore_SCIENCE_2e(), "과학", "2학기 기말");

		// 렌더링 생성 및 세팅

		// 렌더링 생성

		final LineAndShapeRenderer rendererKOR = new LineAndShapeRenderer();

		final LineAndShapeRenderer rendererENG = new LineAndShapeRenderer();

		final LineAndShapeRenderer rendererMATH = new LineAndShapeRenderer();

		final LineAndShapeRenderer rendererSOCIETY = new LineAndShapeRenderer();

		final LineAndShapeRenderer rendererSCIENCE = new LineAndShapeRenderer();

		// 공통 옵션 정의

		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();

		final ItemLabelPosition p_center = new ItemLabelPosition(

				ItemLabelAnchor.CENTER, TextAnchor.CENTER

		);

		final ItemLabelPosition p_below = new ItemLabelPosition(

				ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT

		);

		Font f = new Font("Gulim", Font.BOLD, 14);

		Font axisF = new Font("Gulim", Font.PLAIN, 14);

		// 렌더링 세팅

		// 그래프 국어

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

		// 그래프 영어

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

		// 그래프 수학

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

		// 그래프 사회

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

		// 그래프 과학

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

		// plot 생성

		final CategoryPlot plot = new CategoryPlot();

		// plot 에 데이터 적재

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

		// plot 기본 설정

		plot.setOrientation(PlotOrientation.VERTICAL); // 그래프 표시 방향

		plot.setRangeGridlinesVisible(true); // X축 가이드 라인 표시여부

		plot.setDomainGridlinesVisible(true); // Y축 가이드 라인 표시여부

		// 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		// X축 세팅

		plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정

		plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정

		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정

		// Y축 세팅

		plot.setRangeAxis(new NumberAxis());
		plot.getRangeAxis().setRange(0.0, 100.0);// Y축 종류 설정

		plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정

		// 세팅된 plot을 바탕으로 chart 생성

		final JFreeChart chart = new JFreeChart(plot);

//        chart.setTitle("Overlaid Bar Chart"); // 차트 타이틀

//        TextTitle copyright = new TextTitle("JFreeChart WaferMapPlot", new Font("SansSerif", Font.PLAIN, 9));

//        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);

//        chart.addSubtitle(copyright);  // 차트 서브 타이틀

		return chart;

	}

}

//출처: https://androphil.tistory.com/441 [소림사의 홍반장!]
