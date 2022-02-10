package Assignment2;  
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.*;

import com.jogamp.opengl.*;  
import com.jogamp.opengl.awt.GLCanvas;  
import com.jogamp.opengl.glu.GLU;  
  
import javax.swing.JFrame;

import com.jogamp.opengl.util.Animator;

   
public class OriginalView implements GLEventListener,KeyListener {  
	private GLU glu = new GLU();  
	static Animator anim;
	static Animator anim1;
	static Animator anim2;
	float scale = 1;
	static float 	eyex, 	eyey, 	eyez;//position
	static float 	dirx, 	diry, 	dirz;//direction
	static float 	upx, 	upy, 	upz;//orientation
	
	public static float 	ceyex, 	ceyey, 	ceyez;//position
	public static float 	cdirx, 	cdiry, 	cdirz;//direction
	public static float 	cupx, 	cupy, 	cupz;
	
	double angle;
	double move_angle=0;
	
	
 
	@Override  
	public void display(GLAutoDrawable drawable) {  
		
		//camera value
		eyex =7.0f;	eyey=7.0f; 	eyez=6.0f;
		dirx=1.0f; 	diry=1.0f; 	dirz=-1.0f;
		upx=0.0f; 	upy=1.0f; 	upz=0.0f;
	  
		final GL2 gl = drawable.getGL().getGL2();  
		
		//set camera
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );  
		gl.glLoadIdentity(); 
		glu.gluLookAt(	eyex,	eyey,	eyez,
						dirx,	diry,	dirz,
						upx,	upy,	upz);
		gl.glScalef(scale, scale, scale);
		
		//drawfloor
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(0.0f, 0.4f, 0.3f);
		gl.glVertex3f(-50f, -1.0f, -50.0f);
		gl.glVertex3f(-50f, -1.0f, 50.0f);
		gl.glVertex3f(50f, -1.0f, 50.0f);
		gl.glVertex3f(50f, -1.0f, -50.0f);
		gl.glFlush();
		gl.glEnd();
		
		//create a cube
		gl.glTranslatef(0.0f, 0.0f, 0.0f );     
		gl.glLineWidth(5);
		gl.glBegin(GL2.GL_LINES);
		//set the cube position
		float cube[][] = {	{-4, -1, -1}, {0, -1, -1}, {-4, 1, -1}, {0, 1, -1},
							{-4, -1, 1}, {0, -1, 1}, {-4, 1, 1}, {0, 1, 1}};
		//set the color of the cube
		gl.glColor3f(0f,0f,1f); 
		//draw the cube
		int line[] = {	0, 1,
						2, 3,
						4, 5,
						6, 7,
						0, 2,
						1, 3,
						4, 6,
						5, 7,
						0, 4,
						1, 5,
						7, 3,
						2, 6};
		for (int i=0; i<24; i++) {
				gl.glVertex3f(cube[line[i]][0], cube[line[i]][1], cube[line[i]][2]);
		}
		
		//create a Triangular prism
		//set the Triangular prism position
		float TP[][] = {	{2, -1, -2}, {5, -1, -2}, {2, 3, -2},
							{2, -1, -5}, {5, -1, -5}, {2, 3, -5}};
		//draw the Triangular prism
		int line2[] = {	0, 1,
						0, 2,
						1, 2,
						3, 4,
						4, 5,
						3, 5,
						0, 3,
						1, 4,
						2, 5};
		for (int i=0; i<18; i++) {
			gl.glVertex3f(TP[line2[i]][0], TP[line2[i]][1], TP[line2[i]][2]);
		}
		gl.glFlush();
		gl.glEnd();



   }  
  
   @Override  
   public void dispose(GLAutoDrawable drawable) {  
      //method body  
   }  
      
   @Override  
   public void init(GLAutoDrawable drawable) {  
      // method body 
		   GL2 gl = drawable.getGL().getGL2();
		   gl.glClearColor( 0f, 0f, 0f, 0f );
		   gl.glClearDepth( 1.0f );
		   gl.glEnable( GL2.GL_DEPTH_TEST ); 
		   gl.glDepthFunc( GL2.GL_LEQUAL );
		   gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST ); 
   }  
  
   @Override  
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {    
	   	GL2 gl = drawable.getGL().getGL2();  
		if(height <= 0)  
		height = 1;  
		              
		final float h = (float) width / (float) height;  
		gl.glViewport(0, 0, width, height);  
		gl.glMatrixMode(GL2.GL_PROJECTION);  
		gl.glLoadIdentity();  
		          
		glu.gluPerspective(60, h, 1.5, 50);  //prespective
	
		gl.glMatrixMode(GL2.GL_MODELVIEW);  
		gl.glLoadIdentity();  
  
   }  
   
	//keyboard control
	@Override
	public void keyTyped(KeyEvent ke){}
	
	@Override
	public void keyReleased(KeyEvent ke) {
	
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		int key=ke.getKeyCode();
		   switch(key) {
		   case KeyEvent.VK_LEFT:
			   angle=0.1;
			   move_angle=move_angle+angle;
			   cdirx=(float) ((cdirx-ceyex)*Math.cos(Math.PI*angle)
					   +(cdirz-ceyez)*Math.sin(Math.PI*angle)
					   +ceyex);
			   cdirz=(float) (0.0-(cdirx-ceyex)*Math.sin(Math.PI*angle)
					   +(cdirz-ceyez)*Math.cos(Math.PI*angle)
					   +ceyez);
			   break;
		   case KeyEvent.VK_RIGHT:
			   angle=-0.1;
			   move_angle=move_angle+angle;
			   cdirx=(float) ((cdirx-ceyex)*Math.cos(Math.PI*angle)
					   +(cdirz-ceyez)*Math.sin(Math.PI*angle)
					   +ceyex);
			   cdirz=(float) (0.0-(cdirx-ceyex)*Math.sin(Math.PI*angle)
					   +(cdirz-ceyez)*Math.cos(Math.PI*angle)
					   +ceyez);
			   break;
		   case KeyEvent.VK_UP:
			   ceyex=(float) (ceyex-Math.cos(Math.PI*(-move_angle)));
			   ceyez=(float) (ceyez-Math.sin(Math.PI*(-move_angle)));
			   cdirx=(float) (cdirx-Math.cos(Math.PI*(-move_angle)));
			   cdirz=(float) (cdirz-Math.sin(Math.PI*(-move_angle)));
			   break;
		   case KeyEvent.VK_DOWN:
			   ceyex=(float) (ceyex+Math.cos(Math.PI*(-move_angle)));
			   ceyez=(float) (ceyez+Math.sin(Math.PI*(-move_angle)));
			   cdirx=(float) (cdirx+Math.cos(Math.PI*(-move_angle)));
			   cdirz=(float) (cdirz+Math.sin(Math.PI*(-move_angle)));
		   }
		   System.out.print("\ncdirx: "+cdirx);
		   System.out.print("\tcdirz: "+cdirz);
		   System.out.print("\nceyex: "+ceyex);
		   System.out.print("\tceyez: "+ceyez);
		   System.out.print("\nrad: "+Math.PI*(-move_angle));
		   System.out.print("\tcos: "+Math.cos(Math.PI*(-move_angle)));
	}
	
   
   public OriginalView() {
	   	ceyex =10.0f;	ceyey=0.0f; 	ceyez=0.0f;
		cdirx=0.0f; 	cdiry=0.0f; 	cdirz=0.0f;
		cupx=0.0f; 		cupy=1.0f; 		cupz=0.0f;
		JFrame frame = new JFrame ("Assignment2");
		GLCanvas glcanvas = new GLCanvas();
		GLCanvas glcanvas1 = new GLCanvas();
		GLCanvas glcanvas2 = new GLCanvas();
		
		PerspectiveView persp = new PerspectiveView();
		OrthographicView ortho =new OrthographicView();
		
		glcanvas1= PerspectiveView.glcanvas;
		glcanvas2= OrthographicView.glcanvas;
		glcanvas.addGLEventListener(this);
		glcanvas.addKeyListener(this);
		glcanvas.setPreferredSize(new Dimension(800,500));
		glcanvas1.addGLEventListener(persp);
		glcanvas1.addKeyListener(persp);
		glcanvas1.setPreferredSize(new Dimension(400,400));
		glcanvas2.addGLEventListener(ortho);
		glcanvas2.addKeyListener(ortho);
		glcanvas2.setPreferredSize(new Dimension(400,400));
		
		frame.add(glcanvas, BorderLayout.NORTH);
		frame.add(glcanvas1, BorderLayout.WEST);
		frame.add(glcanvas2, BorderLayout.EAST);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		anim=new Animator(glcanvas);
		anim.start();
		anim1=new Animator(glcanvas1);
		anim1.start();
		anim2=new Animator(glcanvas2);
		anim2.start();
   }
  
     
	public static void main(String[] args) {  
	  new OrthographicView();
	  new PerspectiveView();
	  new OriginalView();
	}  
	
}




