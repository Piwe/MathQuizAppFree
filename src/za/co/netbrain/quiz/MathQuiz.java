package za.co.netbrain.quiz;

import com.codename1.io.Log;
import com.codename1.io.Preferences;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import static com.codename1.ui.CN.*;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.ComponentAnimation;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

public class MathQuiz {

    private Resources res;
    private Form currentForm;
    private Container tabletSurface;
    private Command gridCommand;
    private Command listCommand;

    private Object imageMask;
    private int maskWidth;
    private int maskHeight;
    private Object circleMask;
    private int circleMaskWidth;
    private int circleMaskHeight;

    private int[] colors;
    private Image[] colorBottoms;
    private int currentColor;

    public void init(Object context) {
        // use 2 network threads for slightly faster networking but not too much to overburden the UI
        updateNetworkThreadCount(2);
        res = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);
        if (!isTablet()) {
            Toolbar.setOnTopSideMenu(true);
        }
        Dialog.setDefaultBlurBackgroundRadius(10);

        Log.bindCrashProtection(false);
    }


    private AppComponent createAppButton(FormMain currentForm) {
        AppComponent dc = new AppComponent(currentForm.getDisplayName(), currentForm.getFormIcon(), imageMask,
                maskWidth, maskHeight, colorBottoms[currentColor],
                circleMask, res.getImage("circle-line.png"), circleMaskWidth, circleMaskHeight,
                "Blank" + (currentColor + 1));
        currentColor++;
        if (currentColor == colorBottoms.length) {
            currentColor = 0;
        }

        dc.addActionListener(e -> {
            if (isTablet()) {
                tabletSurface.getAnimationManager().flushAnimation(() -> {
                    tabletSurface.replace(tabletSurface.getComponentAt(0), currentForm.createForm(getCurrentForm()),
                            CommonTransitions.createCover(CommonTransitions.SLIDE_HORIZONTAL, true, 200));
                });
            } else {
                Form previous = getCurrentForm();
                Form f = new Form(currentForm.getDisplayName(), new BorderLayout());
                f.add(CENTER, currentForm.createForm(f));
                f.getToolbar().setBackCommand(" ", new ActionListener<ActionEvent>() {
                    @Override
                    public void actionPerformed(ActionEvent ee) {
                        if (currentForm.onBack()) {
                            previous.showBack();
                        }
                    }
                });

                f.show();
            }
        });
        return dc;
    }

    private void showSplashAnimation() {
        Form splash = new Form(new LayeredLayout());

        callSerially(() -> {
            callSerially(() -> {
                // create image masks for card effects
                Image mask = res.getImage("card-full.png");
                maskWidth = mask.getWidth();
                maskHeight = mask.getHeight() / 4 * 3;
                Image top = mask.subImage(0, 0, maskWidth, maskHeight, true);
                Image bottom = mask.subImage(0, maskHeight, maskWidth, mask.getHeight() / 4, true);
                imageMask = top.createMask();

                Image circleMaskImage = res.getImage("circle.png");
                circleMask = circleMaskImage.createMask();
                circleMaskWidth = circleMaskImage.getWidth();
                circleMaskHeight = circleMaskImage.getHeight();

                colorBottoms = new Image[7];
                colors = new int[colorBottoms.length];
                Object bottomMask = bottom.createMask();
                for (int iter = 0; iter < colorBottoms.length; iter++) {
                    colors[iter] = splash.getUIManager().getComponentStyle("Blank" + (iter + 1)).getBgColor();
                    colorBottoms[iter] = Image.createImage(bottom.getWidth(), bottom.getHeight(), 0xff000000 | colors[iter]);
                    colorBottoms[iter] = colorBottoms[iter].applyMask(bottomMask);
                }
                showMainUI();
            });
        });
    }

    public void start() {
        if (currentForm != null && !(currentForm instanceof Dialog)) {
            currentForm.show();
            return;
        }
        showSplashAnimation();
    }

    private void showMainUI() {
        final Form f = new Form("MathQuiz For Kidz", new BorderLayout());

        FormMain[] pages = new FormMain[]{
            new Addition(),
            new Multiplication(),
            new Subtraction(),
            new Division()
        };

        for (FormMain d : pages) {
            d.init(res);
        }

        Image dukeImage = Image.createImage(circleMaskWidth, circleMaskHeight, 0);
        Graphics g = dukeImage.getGraphics();
        g.drawImage(res.getImage("icon.png"), 0, 0, circleMaskWidth, circleMaskHeight);
        g.drawImage(res.getImage("icon.png"), 0, 0, circleMaskWidth, circleMaskHeight);
        dukeImage = dukeImage.applyMask(circleMask);
        Label duke = new Label(dukeImage);
        Label circle = new Label(res.getImage("circle-line.png"));
        Container dukeImageContainer = LayeredLayout.encloseIn(duke, circle);
        Label name = new Label("Duke");
        name.setUIID("DukeName");
        Container dukeContainer = BorderLayout.west(BoxLayout.encloseY(dukeImageContainer, name));
        dukeContainer.setUIID("ProfileContainer");

        if (isTablet()) {
            Toolbar.setPermanentSideMenu(true);
            f.getToolbar().addComponentToSideMenu(dukeContainer);
            for (FormMain d : pages) {
                f.getToolbar().addComponentToSideMenu(createAppButton(d));
            }
            tabletSurface = f.getContentPane();
            f.add(CENTER, pages[0].createForm(f));
            f.show();
            return;
        }

        Container cnt;
        if (Preferences.get("gridLayout", true)) {
            GridLayout gl = new GridLayout(1);
            gl.setAutoFit(true);
            gl.setHideZeroSized(true);
            cnt = new Container(gl);
            for (FormMain d : pages) {
                cnt.add(createAppButton(d));
            }
        } else {
            cnt = new Container(BoxLayout.y());
            for (FormMain d : pages) {
                cnt.add(createAppButton(d));
            }
        }
        cnt.setScrollableY(true);
        f.add(CENTER, cnt);

        f.getToolbar().addSearchCommand(e -> {
            String t = (String) e.getSource();
            if (t == null) {
                t = "";
            } else {
                t = t.toLowerCase();
            }
            for (Component c : cnt) {
                AppComponent mb = (AppComponent) c;
                boolean show = t.length() == 0 || mb.getText().toLowerCase().indexOf(t) > -1;
                mb.setVisible(show);
                mb.setHidden(!show);
            }
            cnt.animateLayout(200);
        }, 3);

        gridCommand = f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_VIEW_COMFY, 4, e -> {
            if (cnt.getAnimationManager().isAnimating()) {
                return;
            }
            if (!(cnt.getLayout() instanceof GridLayout)) {
                f.removeCommand(gridCommand);
                f.getToolbar().addCommandToRightBar(listCommand);
                f.getToolbar().layoutContainer();
                Preferences.set("gridLayout", true);
                ComponentAnimation[] arr = new ComponentAnimation[cnt.getComponentCount() + 1];
                int offset = 0;
                for (Component c : cnt) {
                    AppComponent mb = (AppComponent) c;
                    arr[offset] = mb.lineToGridStage1();
                    offset++;
                }
                arr[offset] = cnt.createAnimateHierarchy(1000);

                cnt.getAnimationManager().addAnimationAndBlock(ComponentAnimation.compoundAnimation(arr));
                cnt.getParent().revalidate();

                ComponentAnimation[] arr2 = new ComponentAnimation[cnt.getComponentCount()];
                offset = 0;
                for (Component c : cnt) {
                    AppComponent mb = (AppComponent) c;
                    arr2[offset] = mb.lineToGridStage2();
                    offset++;
                }

                cnt.getAnimationManager().addAnimationAndBlock(ComponentAnimation.compoundAnimation(arr2));

                GridLayout gl = new GridLayout(1);
                gl.setAutoFit(true);
                gl.setHideZeroSized(true);
                cnt.setLayout(gl);

                cnt.animateLayout(300);

            }
        });

        listCommand = f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_FORMAT_LIST_BULLETED, 4, e -> {
            if (!(cnt.getLayout() instanceof BoxLayout)) {
                f.removeCommand(listCommand);
                f.getToolbar().addCommandToRightBar(gridCommand);
                f.getToolbar().layoutContainer();
                Preferences.set("gridLayout", false);
                ComponentAnimation[] arr = new ComponentAnimation[cnt.getComponentCount()];
                int offset = 0;
                for (Component c : cnt) {
                    AppComponent mb = (AppComponent) c;
                    arr[offset] = mb.gridToLineStage1();
                    offset++;
                }
                cnt.getAnimationManager().addAnimationAndBlock(ComponentAnimation.compoundAnimation(arr));

                cnt.setLayout(BoxLayout.y());

                arr = new ComponentAnimation[cnt.getComponentCount() + 1];

                offset = 0;
                for (Component c : cnt) {
                    AppComponent mb = (AppComponent) c;
                    arr[offset] = mb.gridToLineStage2();
                    offset++;
                }
                arr[offset] = cnt.createAnimateHierarchy(500);
                cnt.getAnimationManager().addAnimationAndBlock(ComponentAnimation.compoundAnimation(arr));

                cnt.getParent().revalidate();
            }
        });

        if (cnt.getLayout() instanceof GridLayout) {
            f.removeCommand(gridCommand);
        } else {
            f.removeCommand(listCommand);
        }

        f.getToolbar().setVisible(false);
        cnt.setVisible(false);
        for (Component c : cnt) {
            c.setVisible(false);
        }
        f.addShowListener(e -> {
            f.getToolbar().setHeight(0);
            f.getToolbar().setVisible(true);
            f.animateLayoutFadeAndWait(200, 100);
            for (Component c : cnt) {
                c.setY(f.getHeight());
                c.setVisible(true);
                c.getUnselectedStyle().setOpacity(100);
            }
            cnt.setVisible(true);
            cnt.animateLayoutFadeAndWait(400, 100);
            f.removeAllShowListeners();
        });
        f.setTransitionInAnimator(CommonTransitions.createEmpty());
        f.show();
    }

    public void stop() {
        currentForm = getCurrentForm();
    }

    public void destroy() {
    }
}
