package one;
import Budget.BudgetListGUI;
import MalliCode.AdminHome;
import MalliCode.CommunicationPage;
import MalliCode.Search;
import MalliCode.TeamMemberTask;
import gui.TeamHomeGUI;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import querygui.AdminTeamQuery;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jas
 */
public class CardLayouts {
    
    public TimeLineChart tm;
    
    public JFrame frame;
    public CardLayout cards;
    
    public JPanel topPanel;
    public JPanel adminCardPanel;
    public JPanel teamCardPanel;
    public JPanel card1;
    public JPanel login;
   
    public static int user_id;
    
    // Admin Components
    public JPanel adminHome;
    public JButton createProj;
    public JPanel createPanel;
    public JButton searchProject;
    public JButton home;
    public JPanel basePanel;
    public JPanel adminButtonPanel;
    public JPanel searchPanel;
    public JButton searchTeam;
    public JPanel searchTeamPanel;
    
    public JPanel home_panel;
    
    //Team Components
    public JPanel baseTeamPanel;
    public JPanel teamMemberTaskPanel;
    public JButton teamHomeButton;
    public JPanel teamButtonPanel;
    public JPanel teamHome;
    public JButton teamMemberTaskButton;
    public JPanel deliPanel;
    public JButton deliverablesButton;
    public JButton budgetButton;
    public JPanel budgetPanel;
    public JButton communicationButton;
    public JPanel commPanel;
    public JPanel teamHomePanel;
    
    public JLabel header;
    
    public JButton button1;
    public JButton button2;
    
    public JButton button3;
    public JButton button4;
    public JButton project;
     
    public CardLayouts(){
        
        user_id = 0;
        
        frame = new JFrame("FIL Project Manager");
        
        cards = new CardLayout();
        
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        
        adminCardPanel = new JPanel(cards);
        teamCardPanel = new JPanel(cards);
        
        //Admin pages
        
        basePanel = new JPanel(new BorderLayout());
        adminHome = new AdminHome();
        
        adminButtonPanel = new JPanel();
        adminButtonPanel.setBackground(new java.awt.Color(255, 255, 255));
        adminButtonPanel.setLayout(new BoxLayout(adminButtonPanel, BoxLayout.X_AXIS));
        
        basePanel.add(adminButtonPanel,BorderLayout.SOUTH);
        
        adminCardPanel.add(adminHome, "admin home");
        basePanel.add(adminCardPanel, BorderLayout.CENTER);
        
        createPanel = new CreateProject();
        adminCardPanel.add(createPanel, "create panel");
        
        createProj = new JButton("Create Project");
        //adminCardPanel.add(createProj,"create button");
            createProj.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                cards.show(adminCardPanel, "create panel");
            }
        });
        adminButtonPanel.add(createProj);
        
        searchPanel = new ProjectQueryGUI();
        adminCardPanel.add(searchPanel,"search panel");
        
        searchProject = new JButton("Search Project");
        adminButtonPanel.add(searchProject);
            searchProject.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                cards.show(adminCardPanel, "search panel");
            }
        });
        home_panel = new JPanel(new BorderLayout());
        home_panel.setBackground(new java.awt.Color(255, 255, 255));
        
        home = new JButton("Home");
        home_panel.add(home, BorderLayout.EAST);
        basePanel.add(home_panel, BorderLayout.NORTH);
            home.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                cards.show(adminCardPanel, "admin home");
            }
        });
        
        searchTeam = new JButton("Search team member");
        adminButtonPanel.add(searchTeam);
        searchTeamPanel = new AdminTeamQuery();
        adminCardPanel.add(searchTeamPanel,"search team panel");
            searchTeam.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                cards.show(adminCardPanel, "search team panel");
            }
        });
        
        //Team Pages
        baseTeamPanel=new JPanel(new BorderLayout());
        teamHome = new TeamHomeGUI(); 
        teamCardPanel.add(teamHome,"team home");
        
        baseTeamPanel.add(teamCardPanel,BorderLayout.CENTER);
        teamButtonPanel = new JPanel();
        teamButtonPanel.setBackground(new java.awt.Color(255, 255, 255));
        
        teamMemberTaskPanel = new TeamMemberTask();
        teamMemberTaskButton=new JButton("Tasks");
        baseTeamPanel.add(teamButtonPanel,BorderLayout.SOUTH);
        teamButtonPanel.add(teamMemberTaskButton);
        teamCardPanel.add(teamMemberTaskPanel,"team member task panel");
            teamMemberTaskButton.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                cards.show(teamCardPanel, "team member task panel");
            }
        });
        
        deliverablesButton = new JButton("Deliverables");
        teamButtonPanel.add(deliverablesButton);
        deliPanel = new TimeLinePanel();
        teamCardPanel.add(deliPanel,"deli panel");
               deliverablesButton.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                //cards.show(teamCardPanel, "deli panel");
                 tm = new TimeLineChart("Time line chart");
                 tm.setSize(600, 600);
                 tm.setVisible(true);    
            }
        });
               
        budgetButton = new JButton("Budget");
        teamButtonPanel.add(budgetButton);
        budgetPanel = new BudgetListGUI();
        teamCardPanel.add(budgetPanel,"budget");
            budgetButton.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                cards.show(teamCardPanel, "budget");    
            }
        });
        
       communicationButton = new JButton("Communication");
       teamButtonPanel.add(communicationButton);
       commPanel = new CommunicationPage();
       teamCardPanel.add(commPanel,"communication");
        communicationButton.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                cards.show(teamCardPanel, "communication");    
            }
        });
        
        teamHomePanel = new JPanel(new BorderLayout());
        teamHomePanel.setBackground(new java.awt.Color(255, 255, 255));
        
        teamHomeButton = new JButton("Home");
        teamHomePanel.add(teamHomeButton, BorderLayout.EAST);
        baseTeamPanel.add(teamHomePanel,BorderLayout.NORTH);
        teamHomeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(teamCardPanel, "team home"); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        login = new UserRegistration();
        topPanel.add(login, BorderLayout.CENTER);
        //topPanel.add(adminCardPanel, BorderLayout.CENTER);
        //topPanel.add(teamCardPanel, BorderLayout.CENTER);
         
        
        
        
//        button1 = new JButton();
//        button2 = new JButton();
//        adminCardPanel.add(button1);
//        adminCardPanel.add(button2);
        
        
        //button3 = new JButton("Team Members");
        //teamCardPanel.add(button3,"team members");
       
        //project = new JButton("project");
        //adminCardPanel.add(project, "project");
        
        frame.setContentPane(topPanel);
        
        JPanel control = new JPanel();
        header = new JLabel();
        topPanel.add(header, BorderLayout.NORTH);
        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frug.PNG")));
        header.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 3, true));
        header.setBackground(new java.awt.Color(255, 255, 255));
        frame.setSize(600, 600);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void display(){
        if(UserRegistration.login == 1){
            login.setVisible(false);
            topPanel.add(basePanel);
            System.out.println("admin");
            cards.show(adminCardPanel, "admin home");
            //cards.show(adminCardPanel, "create button");
        }
        else{
            login.setVisible(false);
            topPanel.add(baseTeamPanel);
            cards.show(teamCardPanel, "team home");
            user_id = UserRegistration.user_id;
        }   
    }
    
    
    
    public static void main(String[] args){
        CardLayouts cd = new CardLayouts();
    }
    
    
    
}
