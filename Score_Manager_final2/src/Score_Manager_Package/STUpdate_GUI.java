package Score_Manager_Package;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;



import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class STUpdate_GUI {

   private JFrame frame;
   private JTextField txt_id;
   private JTextField txt_pw;

   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               STUpdate_GUI window = new STUpdate_GUI();
//               window.frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   /**
    * Create the application.
    */
   public STUpdate_GUI(String id,String pw) {
      initialize(id,pw);
      this.frame.setVisible(true);
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize(String id,String pw) {
      frame = new JFrame();
      frame.getContentPane().setBackground(new Color(30, 144, 255));
      frame.setBounds(100, 100, 450, 300);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      
      JButton btn_update = new JButton("\uD655\uC778");
      btn_update.setFont(new Font("나눔고딕", Font.PLAIN, 15));
      btn_update.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String id = txt_id.getText();
            String pw = txt_pw.getText();

            Controller controll = new Controller();
            int cnt = controll.updateST(id, pw);
            
            if(cnt > 0) {
               System.out.println("변경 완료");
               String order = "회원정보 수정에 성공했습니다.\n"+"새로운 패스워드 : "+pw;
               Error_Code_GUI Cd_Y_GUI = new Error_Code_GUI(order);

               
               
            }else {
               System.out.println("변경 실패");
               String order = "회원정보 수정에 실패했습니다.";
               Error_Code_GUI Cd_Y_GUI = new Error_Code_GUI(order);

               
               
            }
         }
      });            
            
            
      btn_update.setBounds(86, 207, 100, 30);
      frame.getContentPane().add(btn_update);
      
      JButton btnNewButton_1 = new JButton("\uB4A4\uB85C\uAC00\uAE30");
      btnNewButton_1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

				STMain_GUI STMain_GUI = new STMain_GUI(id,pw);
        	 frame.setVisible(true);
        	 frame.dispose();
        	 
         }
      });
      btnNewButton_1.setBounds(254, 207, 100, 30);
      frame.getContentPane().add(btnNewButton_1);
      
      JLabel lblNewLabel = new JLabel("\uD559\uC0DD\uC815\uBCF4 \uC218\uC815");
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 30));
      lblNewLabel.setToolTipText("");
      lblNewLabel.setBounds(12, 21, 410, 44);
      frame.getContentPane().add(lblNewLabel);
      
      txt_id = new JTextField();
      txt_id.setBounds(148, 87, 206, 30);
      frame.getContentPane().add(txt_id);
      txt_id.setColumns(10);
      
      txt_pw = new JTextField();
      txt_pw.setBounds(148, 132, 206, 30);
      frame.getContentPane().add(txt_pw);
      txt_pw.setColumns(10);
      
      JLabel lblNewLabel_4 = new JLabel("    \uC544\uC774\uB514 :");
      lblNewLabel_4.setFont(new Font("나눔고딕", Font.PLAIN, 15));
      lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_4.setBounds(52, 91, 102, 18);
      frame.getContentPane().add(lblNewLabel_4);
      
      JLabel lblNewLabel_3 = new JLabel("\uBCC0\uACBD\uD560 \uBE44\uBC00\uBC88\uD638 :");
      lblNewLabel_3.setFont(new Font("나눔고딕", Font.PLAIN, 15));
      lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3.setBounds(25, 134, 112, 21);
      frame.getContentPane().add(lblNewLabel_3);
      
      JLabel lbl_background = new JLabel("");
      lbl_background.setIcon(new ImageIcon("C:\\Users\\SM2103\\Desktop\\\uB85C\uACE0\uCEA1\uCCD0_2.png"));
      lbl_background.setHorizontalAlignment(SwingConstants.LEFT);
      lbl_background.setVerticalAlignment(SwingConstants.TOP);
      lbl_background.setFont(new Font("G마켓 산스 TTF Bold", Font.BOLD, 12));
      lbl_background.setOpaque(true);
      lbl_background.setBackground(new Color(255, 255, 255));
      lbl_background.setBounds(12, 10, 410, 241);
      frame.getContentPane().add(lbl_background);
   }
}