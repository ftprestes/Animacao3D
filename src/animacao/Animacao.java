package animacao;

import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;
import javax.swing.JFrame;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Sphere;
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
        
        Musica musica = new Musica("musica.wav");
        musica.start();
         
    }

    public static void main(String[] args) {
        Animacao be = new Animacao();
    }

    //In this method, the objects for the scene are generated and added to 
    //the SimpleUniverse.
    public void createSceneGraph(SimpleUniverse su) {

        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene capitao = null;
        Scene capitao2 = null;
        Scene hulk = null;
        Scene doom = null;
        Scene ironMan = null;
        Scene thor = null;
        Scene carro = null;
        Scene spiderMan = null;
        
        try {
            capitao = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/America/Captain_America_The_First_Avenger.obj");
            capitao2 = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/America/capitao.obj");
            hulk = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/Hulk/hulk.obj");
            carro = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/Car/car.obj");
            doom = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/Doom/Doctor_Doom.obj");
            ironMan = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/Ironman/ironMAn.obj");
            thor = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/Thor/Thor_Avengers.obj");
            spiderMan = f.load("/home/prestes/NetBeansProjects/Animacao/Objetos/Spiderman/Spiderman.obj");
            
        } catch (Exception e) {
            System.out.println("Error Loading Images:" + e);
        }

        //modificações capitao america1
        Transform3D capitaoObject = new Transform3D();
        capitaoObject.rotY(Math.PI * 0.5);
        capitaoObject.setScale(0.85);
        capitaoObject.setTranslation(new Vector3f(-3f, -1.4f, -9f));

        //modificações capitao america2
        Transform3D capitao2Object = new Transform3D();
        capitao2Object.rotY(Math.PI * 1.5);
        capitao2Object.setScale(0.85);
        capitao2Object.setTranslation(new Vector3f(6f, -1.4f, -9f));

        //modificações hulk
        Transform3D hulkObject = new Transform3D();
        hulkObject.rotY(Math.PI * 2);
        hulkObject.setScale(1.4);
        hulkObject.setTranslation(new Vector3f(-2f, -4f, -51f));

        //modificações doom
        Transform3D doomObject = new Transform3D();
        doomObject.rotY(Math.PI * 1.5);
        doomObject.setScale(0.85);
        doomObject.setTranslation(new Vector3f(2f, -1.4f, -9f));

        //modificações ironman
        Transform3D ironManObject = new Transform3D();
        ironManObject.rotY(Math.PI * 2);
        ironManObject.setScale(0.85);
        ironManObject.setTranslation(new Vector3f(4f, -1.4f, -30f));

        //modificações thor
        Transform3D thorObject = new Transform3D();
        thorObject.rotY(Math.PI * 1.82);
        thorObject.setScale(0.85);
        thorObject.setTranslation(new Vector3f(10f, -3f, -30f));
        
        //modificações spiderMan
        Transform3D spiderManObject = new Transform3D();
        spiderManObject.rotY(Math.PI * 1.82);
        spiderManObject.setScale(0.85);
        spiderManObject.setTranslation(new Vector3f(12f, -3f, -30f));
        
        //modificações carro
        Transform3D carroObject = new Transform3D();
        carroObject.rotY(Math.PI * 0.7);
        carroObject.setScale(5);
        carroObject.setTranslation(new Vector3f(3f, -3f, -30f));
       
        //criação do grupo capitao
        TransformGroup capitaoGroup = new TransformGroup(capitaoObject);
        TransformGroup allCapitaoObjects = new TransformGroup();
        //movimento do capitão    
        Alpha alphaCapitao = new Alpha(1, Alpha.INCREASING_ENABLE, 1000, 1000, 4000, 100, 0, 0, 0, 0);
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
        quatsCapitao[8] = new Quat4f(0f, -8f, 0f, 2f);
        quatsCapitao[9] = new Quat4f(0f, -9f, 0f, -2f);
        quatsCapitao[10] = new Quat4f(0f, -10f, 0f, 2f);
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

        //criação do grupo capitão2
        TransformGroup capitao2Group = new TransformGroup(capitao2Object);
        TransformGroup allCapitao2Objects = new TransformGroup();
        //movimento do capitão2
        Alpha alphaCapitao2 = new Alpha(1, Alpha.INCREASING_ENABLE, 6000, 1000, 2000, 100, 0, 0, 0, 0);
        Transform3D axisCapitao2 = new Transform3D();
        axisCapitao2.rotY(Math.PI / 2);
        PositionInterpolator capitao2Movement = new PositionInterpolator(alphaCapitao2, allCapitao2Objects, axisCapitao2, 0f, -3.8f);
        allCapitao2Objects.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        capitao2Movement.setSchedulingBounds(boundsCapitao);
        capitao2Group.addChild(allCapitao2Objects);
        allCapitao2Objects.addChild(capitao2Movement);
        allCapitao2Objects.addChild(capitao2.getSceneGroup());

        //criação do grupo hulk
        TransformGroup hulkGroup = new TransformGroup(hulkObject);
        TransformGroup allHulkObjects = new TransformGroup();
        //movimento do hulk
        Alpha alphaHulk = new Alpha(1, Alpha.INCREASING_ENABLE, 6000, 2000, 4000, 100, 0, 0, 0, 0);
        Transform3D axisHulk = new Transform3D();
        axisHulk.rotZ((float) Math.toRadians(720));
        float[] knotsHulk = {0.0f, 0.05f, 0.1f, 0.15f, 0.2f, 0.25f, 0.3f, 0.35f, 0.4f, 0.45f, 0.5f, 0.55f, 0.6f, 0.65f, 0.7f, 0.75f, 0.8f, 0.85f, 0.9f, 0.95f, 1f};
        float[] scalesHulk = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
        Quat4f[] quatsHulk = new Quat4f[21];
        Point3f[] positionsHulk = new Point3f[21];

        quatsHulk[0] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[1] = new Quat4f(0f, 0f, 0f, 0.f);
        quatsHulk[2] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[3] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[4] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[5] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[6] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[7] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[8] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[9] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[10] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[11] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[12] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[13] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[14] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[15] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[16] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[17] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[18] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[19] = new Quat4f(0f, 0f, 0f, 0f);
        quatsHulk[20] = new Quat4f(0f, 0f, 0f, 0f);

        positionsHulk[0] = new Point3f(0f, 0f, 0f);
        positionsHulk[1] = new Point3f(0f, 0.1f, 2f);
        positionsHulk[2] = new Point3f(0f, 0.3f, 4f);
        positionsHulk[3] = new Point3f(0f, 0.5f, 6f);
        positionsHulk[4] = new Point3f(0f, 0.7f, 8f);
        positionsHulk[5] = new Point3f(0f, 0.9f, 10f);
        positionsHulk[6] = new Point3f(0f, 1.1f, 12f);
        positionsHulk[7] = new Point3f(0f, 1.3f, 14f);
        positionsHulk[8] = new Point3f(0f, 1.5f, 16f);
        positionsHulk[9] = new Point3f(0f, 1.7f, 18f);
        positionsHulk[10] = new Point3f(0f, 1.9f, 20f);
        positionsHulk[11] = new Point3f(0f, 1.9f, 22f);
        positionsHulk[12] = new Point3f(0f, 1.9f, 24f);
        positionsHulk[13] = new Point3f(0f, 1.9f, 26f);
        positionsHulk[14] = new Point3f(0f, 1.9f, 29f);
        positionsHulk[15] = new Point3f(0f, 1.9f, 29f);
        positionsHulk[16] = new Point3f(0f, 1.9f, 29f);
        positionsHulk[17] = new Point3f(0f, 1.9f, 29f);
        positionsHulk[18] = new Point3f(0f, 1.9f, 29f);
        positionsHulk[19] = new Point3f(0f, 1.9f, 29f);
        positionsHulk[20] = new Point3f(0f, 1.9f, 29f);

        RotPosScalePathInterpolator hulkMovement = new RotPosScalePathInterpolator(alphaHulk, allHulkObjects, axisHulk, knotsHulk, quatsHulk,
                positionsHulk, scalesHulk);
        BoundingSphere boundsHulk = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        allHulkObjects.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        hulkMovement.setSchedulingBounds(boundsHulk);
        hulkGroup.addChild(allHulkObjects);
        allHulkObjects.addChild(hulkMovement);
        allHulkObjects.addChild(hulk.getSceneGroup());

        //criação do grupo doom
        TransformGroup doomGroup = new TransformGroup(doomObject);
        //movimento de rotação do doom
        TransformGroup allDoomObjects1 = new TransformGroup();
        Alpha alphaDoom1 = new Alpha(1, Alpha.INCREASING_ENABLE, 2000, 2000, 2000, 4000, 0, 0, 0, 0);
        Transform3D axisDoom1 = new Transform3D();
        axisDoom1.rotY(Math.PI / 2);
        RotationInterpolator doomMovement1 = new RotationInterpolator(alphaDoom1, allDoomObjects1, axisDoom1, 0, 3);
        allDoomObjects1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        doomMovement1.setSchedulingBounds(boundsCapitao);
        doomGroup.addChild(allDoomObjects1);
        allDoomObjects1.addChild(doomMovement1);
        //allDoomObjects1.addChild(doom.getSceneGroup());

        //movimento de "ir para trás do doom com pathInterpolator"
        TransformGroup allDoomObjects2 = new TransformGroup();
        Alpha alphaDoom2 = new Alpha(1, Alpha.INCREASING_ENABLE, 6700, 2000, 4000, 100, 0, 0, 0, 0);

        Transform3D axisDoom2 = new Transform3D();
        float[] knotsDoom = {0.0f, 0.05f, 0.1f, 0.15f, 0.2f, 0.25f, 0.3f, 0.35f, 0.4f, 0.45f, 0.5f, 0.55f, 0.6f, 0.65f, 0.7f, 0.75f, 0.8f, 0.85f, 0.9f, 0.95f, 1f};
        float[] scalesDoom = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
        Quat4f[] quatsDoom = new Quat4f[21];
        Point3f[] positionsDoom = new Point3f[21];

        quatsDoom[0] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[1] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[2] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[3] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[4] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[5] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[6] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[7] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[8] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[9] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[10] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[11] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[12] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[13] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[14] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[15] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[16] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[17] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[18] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[19] = new Quat4f(0f, 0f, 0f, 0f);
        quatsDoom[20] = new Quat4f(0f, 0f, 0f, 0f);

        positionsDoom[0] = new Point3f(0f, 0f, 0f);
        positionsDoom[1] = new Point3f(0f, 0f, -0.5f);
        positionsDoom[2] = new Point3f(0f, 0f, -1f);
        positionsDoom[3] = new Point3f(0f, 0f, -1.5f);
        positionsDoom[4] = new Point3f(0f, 0f, -2f);
        positionsDoom[5] = new Point3f(0f, 0f, -2.5f);
        positionsDoom[6] = new Point3f(0f, 0f, -3f);
        positionsDoom[7] = new Point3f(0f, 0f, -3.5f);
        positionsDoom[8] = new Point3f(0f, 0f, -4f);
        positionsDoom[9] = new Point3f(0f, 0f, -4.5f);
        positionsDoom[10] = new Point3f(0f, 0f, -5f);
        positionsDoom[11] = new Point3f(-1f, 0f, -5f);
        positionsDoom[12] = new Point3f(-2f, 0f, -5f);
        positionsDoom[13] = new Point3f(-3f, 0f, -5f);
        positionsDoom[14] = new Point3f(-4f, 0f, -5f);
        positionsDoom[15] = new Point3f(-5f, 0f, -5f);
        positionsDoom[16] = new Point3f(-6f, 0f, -5f);
        positionsDoom[17] = new Point3f(-7f, 0f, -5f);
        positionsDoom[18] = new Point3f(-8f, 0f, -5f);
        positionsDoom[19] = new Point3f(-9f, 0f, -5f);
        positionsDoom[20] = new Point3f(-10f, 0f, -5f);

        RotPosScalePathInterpolator doomMovement2 = new RotPosScalePathInterpolator(alphaDoom2, allDoomObjects2, axisDoom2, knotsDoom, quatsDoom,
                positionsDoom, scalesDoom);
        BoundingSphere boundsDoom = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        allDoomObjects2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        doomMovement2.setSchedulingBounds(boundsDoom);
        allDoomObjects1.addChild(allDoomObjects2);
        allDoomObjects2.addChild(doomMovement2);
        allDoomObjects2.addChild(doom.getSceneGroup());

        //criação do grupo IronMan
        TransformGroup ironManGroup = new TransformGroup(ironManObject);
        //movimento do ironMan
        TransformGroup allIronManObjects = new TransformGroup();
        Alpha alphaIronMan = new Alpha(1, Alpha.INCREASING_ENABLE, 10000, 1000, 8000, 3000, 0, 0, 0, 0);
        Transform3D axisIronMan = new Transform3D();
        //axisIronMan.rotZ(Math.PI / 2);
        float[] knotsIronMan = {0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1f};
        Point3f[] positionsIronMan = new Point3f[11];
        positionsIronMan[0] = new Point3f(0f, 0f, 5f);
        positionsIronMan[1] = new Point3f(-2f, 3f, 7f);
        positionsIronMan[2] = new Point3f(-4f, 3f, 8f);
        positionsIronMan[3] = new Point3f(-6f, 3f, 10f);
        positionsIronMan[4] = new Point3f(-8f, 3f, 13f);
        positionsIronMan[5] = new Point3f(-10f, 3f, 10f);
        positionsIronMan[6] = new Point3f(-8f, 3f, 8f);
        positionsIronMan[7] = new Point3f(-7f, 3f, 7f);
        positionsIronMan[8] = new Point3f(-6f, 3f, 5f);
        positionsIronMan[9] = new Point3f(-5f, 3f, 5f);
        positionsIronMan[10] = new Point3f(-5f, -1f, 5f);

        PositionPathInterpolator ironManMovement = new PositionPathInterpolator(alphaIronMan, allIronManObjects, axisIronMan, knotsIronMan, positionsIronMan);
        allIronManObjects.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        ironManMovement.setSchedulingBounds(boundsHulk);
        ironManGroup.addChild(allIronManObjects);
        allIronManObjects.addChild(ironManMovement);
        allIronManObjects.addChild(ironMan.getSceneGroup());

        //criação do grupo thor
        TransformGroup thorGroup = new TransformGroup(thorObject);
        thorGroup.addChild(thor.getSceneGroup());
        
        //ciação do grupo spiderMan
        TransformGroup spiderManGroup = new TransformGroup(spiderManObject);
        spiderManGroup.addChild(spiderMan.getSceneGroup());
        
        //ciação do grupo carro
        TransformGroup carroGoup = new TransformGroup(carroObject);
        TransformGroup allCarroObjects = new TransformGroup();
        //movimento do carro
        Alpha alphaCarro = new Alpha(1, Alpha.INCREASING_ENABLE, 9200, 500, 2000, 10000, 0, 0, 0, 0);
        Transform3D axisCarro = new Transform3D();   
        PositionInterpolator carroMovement = new PositionInterpolator
            (alphaCarro, allCarroObjects, axisCarro, 0f, -1f);
        allCarroObjects.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        carroMovement.setSchedulingBounds(boundsHulk);
        carroGoup.addChild(allCarroObjects);
        allCarroObjects.addChild(carroMovement);
        allCarroObjects.addChild(carro.getSceneGroup());
        
        //adição da luz 
        Color3f ambientColourSphere = new Color3f(0.2f, 0.2f, 0.0f);
        Color3f emissiveColourSphere = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f diffuseColourSphere = new Color3f(0.4f, 0.4f, 0.0f);
        Color3f specularColourSphere = new Color3f(0.8f, 0.8f, 0.0f);
        float shininessSphere = 120.0f;
        Appearance sphereApp = new Appearance();
        TransparencyAttributes ta1 = new TransparencyAttributes();
        ta1.setTransparencyMode(TransparencyAttributes.BLENDED);
        ta1.setTransparency(1);
        sphereApp.setTransparencyAttributes(ta1);
        sphereApp.setMaterial(new Material(ambientColourSphere, emissiveColourSphere,
                diffuseColourSphere, specularColourSphere, shininessSphere));
        Sphere mySphere = new Sphere(0.3f, Sphere.GENERATE_NORMALS, 100, sphereApp);
        Transform3D tfSphere = new Transform3D();
        //modificações luz
         //Generate interpolated transparency.
        
        tfSphere.setScale(0.85);
        tfSphere.setTranslation(new Vector3f(8f, -1.4f, -30f));
        //grupo da luz
        TransformGroup tgSphere = new TransformGroup(tfSphere);
        tgSphere.addChild(mySphere);
        TransformGroup allTgSphereObjects = new TransformGroup();
        PositionPathInterpolator sphereMovement = new PositionPathInterpolator(alphaIronMan, allTgSphereObjects, axisIronMan, knotsIronMan, positionsIronMan);
        allTgSphereObjects.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sphereMovement.setSchedulingBounds(boundsHulk);

        allTgSphereObjects.addChild(sphereMovement);
        allTgSphereObjects.addChild(tgSphere);

        //Add everything to the scene.
        BranchGroup theScene = new BranchGroup();
        theScene.addChild(capitaoGroup);
        theScene.addChild(capitao2Group);
        theScene.addChild(hulkGroup);
        theScene.addChild(doomGroup);
        theScene.addChild(ironManGroup);
        theScene.addChild(thorGroup);
        theScene.addChild(spiderManGroup);
        theScene.addChild(carroGoup);
        theScene.addChild(allTgSphereObjects);
        
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
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
        //Directional light (to be rotated).
        Color3f lightColour = new Color3f(1.0f, 1.0f, 1.0f);
        Vector3f lightDir = new Vector3f(0.0f, 0.0f, -1.0f);
        DirectionalLight light = new DirectionalLight(lightColour, lightDir);
        light.setInfluencingBounds(bounds);

        //The transformation group for the directional light and its rotation.
        TransformGroup tfmLight = new TransformGroup();
        tfmLight.addChild(light);

        //The Alpha for the rotation.
        Alpha alphaLight = new Alpha(-1, 4000);
        //The rotation
        RotationInterpolator rot = new RotationInterpolator(alphaLight, tfmLight,
                new Transform3D(),
                0.0f, (float) Math.PI * 2);
        rot.setSchedulingBounds(bounds);

        tfmLight.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tfmLight.addChild(rot);

        bgLight.addChild(tfmLight);

        //Light no. 3: a spotlight.
        Color3f lightColour3 = new Color3f(1f, 1f, 1f);
        SpotLight light3 = new SpotLight(lightColour3,
                new Point3f(0.0f, 0.0f, 1.0f),
                new Point3f(0.1f, 0.1f, 0.01f),
                new Vector3f(0.0f, 0.0f, -1.0f),
                (float) (Math.PI / 20),
                0.0f);

        light3.setInfluencingBounds(bounds);
        bgLight.addChild(light3);

        //Light no. 4: ambient light.
        Color3f lightColour4 = new Color3f(0.2f, 0.2f, 0.2f);
        AmbientLight light4 = new AmbientLight(lightColour4);
        light4.setInfluencingBounds(bounds);
        bgLight.addChild(light4);

        su.addBranchGraph(bgLight);
    }
}
