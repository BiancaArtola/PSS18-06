PROYECTO TDP - Universidad Nacional del Sur
Comisión Nº6
Aldana Casé (104870), Facundo Pierrestegui (99694), Stefania Heinrich (106205)

- Proyecto compilado en computadoras con la siguiente configuración:
-- GNU\Linux Manjaro x64 18.0, Eclipse v.4.9, OpenJDK 1.8.0_192-b26
-- Windows 10 Pro x64 version 1803, Eclipse v.4.8.0, Java 1.8.0_191-b12
-- Windows 10 Education x64 version 1803, Eclipse v.4.9.0, Java 1.8.0_191-b12

Durante la ejecución del juego se puede visualizar el siguiente error que no afecta en ningún momento la jugabilidad del mismo.
Luego de buscar información llegamos a la conclusión de que el error se produce en la libreria AWT: cuando quiere repintar la interfaz no encuentra
algún objeto que haya sido eliminado previamente.

Exception in thread "AWT-EventQueue-0" java.lang.ArrayIndexOutOfBoundsException: No such child: 83
	at java.awt.Container.getComponent(Unknown Source)
	at javax.swing.JComponent.rectangleIsObscured(Unknown Source)
	at javax.swing.JComponent.paint(Unknown Source)
	at javax.swing.JComponent.paintToOffscreen(Unknown Source)
	at javax.swing.RepaintManager$PaintManager.paintDoubleBuffered(Unknown Source)
	at javax.swing.RepaintManager$PaintManager.paint(Unknown Source)
	at javax.swing.RepaintManager.paint(Unknown Source)
	at javax.swing.JComponent._paintImmediately(Unknown Source)
	at javax.swing.JComponent.paintImmediately(Unknown Source)
	at javax.swing.RepaintManager$4.run(Unknown Source)
	at javax.swing.RepaintManager$4.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(Unknown Source)
	at javax.swing.RepaintManager.paintDirtyRegions(Unknown Source)
	at javax.swing.RepaintManager.paintDirtyRegions(Unknown Source)
	at javax.swing.RepaintManager.prePaintDirtyRegions(Unknown Source)
	at javax.swing.RepaintManager.access$1200(Unknown Source)
	at javax.swing.RepaintManager$ProcessingRunnable.run(Unknown Source)
	at java.awt.event.InvocationEvent.dispatch(Unknown Source)
	at java.awt.EventQueue.dispatchEventImpl(Unknown Source)
	at java.awt.EventQueue.access$500(Unknown Source)
	at java.awt.EventQueue$3.run(Unknown Source)
	at java.awt.EventQueue$3.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(Unknown Source)
	at java.awt.EventQueue.dispatchEvent(Unknown Source)
	at java.awt.EventDispatchThread.pumpOneEventForFilters(Unknown Source)
	at java.awt.EventDispatchThread.pumpEventsForFilter(Unknown Source)
	at java.awt.EventDispatchThread.pumpEventsForHierarchy(Unknown Source)
	at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
	at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
	at java.awt.EventDispatchThread.run(Unknown Source)