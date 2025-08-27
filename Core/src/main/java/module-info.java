module Core {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires javafx.controls;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires spring.aop;
    requires spring.expression;

    exports dk.sdu.cbse.main;
    opens dk.sdu.cbse.main to spring.core, javafx.graphics, spring.beans, spring.context;
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    }