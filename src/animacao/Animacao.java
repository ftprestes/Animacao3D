package animacao;

import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;
import javax.swing.JFrame;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.image.TextureLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * An example for using an image loaded from a file as a background. This
 * program needs the file darkclouds.jpg.
 * 
* @author Frank Klawonn Last change 17.07.2005
 */
public class Animacao extends JFrame {

    //The canvas to be drawn upon.
    public Canvas3D myCanvas3D;

    public Animacao() {
        //Mechanism for closing the window and ending the program.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Default settings for the viewer parameters.
        myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        //Construct the SimpleUniverse:
        //First generate it using the Canvas.
        SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);

        //Default position of the viewer.
        simpUniv.getViewingPlatform().setNominalViewingTransform();

        //The scene is generated in this method.
        createSceneGraph(simpUniv);

        //Add some light to the scene.
        addLight(simpUniv);

        //The following three lines enable navigation through the scene using the mouse.
        OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);

        //Show the canvas/window.
        setTitle("An image as a background");
        setSize(1400, 700);
        getContentPane().add("Center", myCanvas3D);
        setVisible(true);

    }

    public static void main(String[] args) {
        Animacao be = new Animacao();
    }

    //In this method, the objects for the scene are generated and added to 
    //the SimpleUniverse.
    public void createSceneGraph(SimpleUniverse su) {

        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene capitao = null;
        Scene hulk = null;
        Scene doom = null;

        try {
            capitao = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/America/Captain_America_The_First_Avenger.obj");
            hulk = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/Hulk/Hulk_Avengers.obj");
            doom = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/Doom/Doctor_Doom.obj");

        } catch (Exception e) {
            System.out.println("Error Loading Images:" + e);
        }

        //modificações capitao america
        Transform3D capitaoObject = new Transform3D();
        capitaoObject.rotY(Math.PI * 0.5);
        capitaoObject.setScale(0.85);
        capitaoObject.setTranslation(new Vector3f(-3f, -1.4f, -9f));

        //modificações hulk
        Transform3D hulkObject = new Transform3D();
        hulkObject.rotY(Math.PI * 1.5);
        hulkObject.setScale(0.75);
        hulkObject.setTranslation(new Vector3f(3f, -1.4f, -9f));

        //modificações doom
        Transform3D doomObject = new Transform3D();
        doomObject.rotY(Math.PI * 1.5);
        doomObject.setScale(0.85);
        doomObject.setTranslation(new Vector3f(2f, -1.4f, -9f));

        //criação do grupo capitao
        TransformGroup capitaoGroup = new TransformGroup(capitaoObject);
        TransformGroup allCapitaoObjects = new TransformGroup();
        //movimento do capitão    
        //RotPosScalePathInterpolator(Alpha alpha, TransformGroup target, 
        //Transform3D axisOfRotPosScale, float[] knots, Quat4f[] quats, Point3f[] 
        //positions, float[] scales)
        Alpha alphaCapitao = new Alpha(-1, Alpha.INCREASING_ENABLE, 1000, 1000, 4000, 100, 0, 0, 0, 0);
        Transform3D axisCapitao = new Transform3D();
        float[] knotsCapitao = {0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
        float[] scalesCapitao = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};

        Quat4f[] quatsCapitao = new Quat4f[11];
        Point3f[] positionsCapitao = new Point3f[11];

        quatsCapitao[0] = new Quat4f(0f, 0f, 0f, 0f);
        quatsCapitao[1] = new Quat4f(0f, 0f, 0f, 0f);
        quatsCapitao[2] = new Quat4f(1f, 0f, 0f, 0f);
        quatsCapitao[3] = new Quat4f(2f, 0f, 0f, 0f);
        quatsCapitao[4] = new Quat4f(3f, -2f, 0f, 2f);
        quatsCapitao[5] = new Quat4f(4f, -4f, 0f, 3f);
        quatsCapitao[6] = new Quat4f(5f, -6f, 0f, 4f);
        quatsCapitao[7] = new Quat4f(6f, -7f, 0f, 5f);
        quatsCapitao[8] = new Quat4f(0f, -8f, 0f, 0f);
        quatsCapitao[9] = new Quat4f(0f, -9f, 0f, 0f);
        quatsCapitao[10] = new Quat4f(0f, -10f, 0f, 0f);

        positionsCapitao[0] = new Point3f(0f, 0f, 0f);
        positionsCapitao[1] = new Point3f(0f, 1f, 1f);
        positionsCapitao[2] = new Point3f(0f, 2f, 2f);
        positionsCapitao[3] = new Point3f(0f, 3f, 3f);
        positionsCapitao[4] = new Point3f(0f, 4f, 4f);
        positionsCapitao[5] = new Point3f(0f, 3f, 5f);
        positionsCapitao[6] = new Point3f(0f, 2f, 6f);
        positionsCapitao[7] = new Point3f(0f, 1f, 7f);
        positionsCapitao[8] = new Point3f(0f, 0f, 8f);
        positionsCapitao[9] = new Point3f(0f, 0f, 9f);
        positionsCapitao[10] = new Point3f(0f, 0f, 10f);

        RotPosScalePathInterpolator capitaoMovement = new RotPosScalePathInterpolator(alphaCapitao, allCapitaoObjects, axisCapitao, knotsCapitao, quatsCapitao,
                positionsCapitao, scalesCapitao);
        BoundingSphere boundsCapitao = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        allCapitaoObjects.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        capitaoMovement.setSchedulingBounds(boundsCapitao);
        capitaoGroup.addChild(allCapitaoObjects);
        allCapitaoObjects.addChild(capitaoMovement);
        allCapitaoObjects.addChild(capitao.getSceneGroup());

        //criação do grupo hulk
        TransformGroup hulkGroup = new TransformGroup(hulkObject);
        hulkGroup.addChild(hulk.getSceneGroup());

        //criação do grupo doom
        TransformGroup doomGroup = new TransformGroup(doomObject);
        doomGroup.addChild(doom.getSceneGroup());

        //Add everything to the scene.
        BranchGroup theScene = new BranchGroup();
        theScene.addChild(capitaoGroup);
        //theScene.addChild(hulkGroup);
        theScene.addChild(doomGroup);

        //The bounding region for the background.
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
        //Load the background image.
        TextureLoader textureLoad = new TextureLoader("cenario4.jpg", null);
        //Define the image as the background and add it to the scene.
        Background bgImage = new Background(textureLoad.getImage());
        bgImage.setApplicationBounds(bounds);
        theScene.addChild(bgImage);

        theScene.compile();

        //Add the scene to the universe.
        su.addBranchGraph(theScene);

    }

    //Some light is added to the scene here.
    public void addLight(SimpleUniverse su) {

        BranchGroup bgLight = new BranchGroup();

        //Directional light.
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
        Color3f lightColour1 = new Color3f(1.0f, 1.0f, 1.0f);
        Vector3f lightDir1 = new Vector3f(-1.0f, 0.0f, -0.1f);
        DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
        light1.setInfluencingBounds(bounds);

        bgLight.addChild(light1);

        //Ambient light.
        Color3f ambientLightColour = new Color3f(0.5f, 0.5f, 0.5f);
        AmbientLight ambLight = new AmbientLight(ambientLightColour);
        ambLight.setInfluencingBounds(bounds);
        bgLight.addChild(ambLight);

        su.addBranchGraph(bgLight);

    }

}
