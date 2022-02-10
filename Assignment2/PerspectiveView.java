package Assignment2;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogamp.opengl.*;  
import com.jogamp.opengl.awt.GLCanvas;  
import com.jogamp.opengl.glu.GLU;  
  
import javax.swing.JFrame;


public class PerspectiveView  implements GLEventListener, KeyListener {
	private GLU glu = new GLU();  
	float scale = 1;
	static float 	eyex, 	eyey, 	eyez;//position
	static float 	dirx, 	diry, 	dirz;//direction
	static float 	upx, 	upy, 	upz;//orientation
	static GLCanvas glcanvas = new GLCanvas();
	
	public PerspectiveView() {
		
	}
 
	@Override  
	public void display(GLAutoDrawable drawable) {  
		
		//camera value
		eyex=Assignment2.OriginalView.ceyex;
		eyey=Assignment2.OriginalView.ceyey;
		eyez=Assignment2.OriginalView.ceyez;
		dirx=Assignment2.OriginalView.cdirx;
		diry=Assignment2.OriginalView.cdiry;
		dirz=Assignment2.OriginalView.cdirz;
		upx=Assignment2.OriginalView.cupx;
		upy=Assignment2.OriginalView.cupy;
		upz=Assignment2.OriginalView.cupz;
		
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
			              
			final float h = (float) width / (float) height;  
			gl.glViewport(0, 0, width, height);  
			gl.glMatrixMode(GL2.GL_PROJECTION);  
			gl.glLoadIdentity();  
			          
			glu.gluPerspective(60, h, 1.5, 50);  //prespective
			
		
			gl.glMatrixMode(GL2.GL_MODELVIEW);  
			gl.glLoadIdentity();  
	  
	   }  
	   @Override
	   public void keyTyped(KeyEvent ke) {}
	   
	   @Override
	   public void keyReleased(KeyEvent ke) {}
	   
	   @Override
	   public void keyPressed(KeyEvent ke) {
		   
	   }
	   
	
}
