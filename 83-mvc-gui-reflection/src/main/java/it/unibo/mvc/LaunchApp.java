package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberStandardOutputView;
import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main() throws
        ClassNotFoundException,
        NoSuchMethodException,
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException{
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        for (final var view: List.of("Swing","StandardOutput")) {
            final var element = Class.forName("it.unibo.mvc.view.DrawNumber"+view+"View");
            for (int i=0;i<3;i++) {
                final var newelement=element.getConstructor().newInstance();
                if (DrawNumberView.class.isAssignableFrom(element)) {
                    app.addView((DrawNumberView) newelement);
                } else {
                    throw new IllegalStateException(
                        element.getClass() + " is not a subclass of " + DrawNumberView.class
                    );
                }
            }
        }
    }
}
