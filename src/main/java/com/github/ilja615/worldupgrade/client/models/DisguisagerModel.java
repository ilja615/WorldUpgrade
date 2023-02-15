package com.github.ilja615.worldupgrade.client.models;

import com.github.ilja615.worldupgrade.entity.Disguisager;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.AbstractIllager;

public class DisguisagerModel<T extends Disguisager> extends IllagerModel<Disguisager> implements ArmedModel, HeadedModel
{
    private final ModelPart froghat;
    private final ModelPart froghateye1;
    private final ModelPart froghateye2;
    private final ModelPart arms;
    private final ModelPart rightArm;
    private final ModelPart leftArm;

    public boolean isDisguisagerDisguised = false;
    public boolean isDisguisagerBlowingHorn = false;

    public DisguisagerModel(ModelPart part)
    {
        super(part);

        this.froghat = part.getChild("froghat");
        this.froghateye1 = part.getChild("froghateye1");
        this.froghateye2 = part.getChild("froghateye2");

        this.arms = part.getChild("arms");
        this.leftArm = part.getChild("left_arm");
        this.rightArm = part.getChild("right_arm");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition1.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.45F)), PartPose.ZERO);
        partdefinition1.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(0.0F, -2.0F, 0.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F).texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition partdefinition2 = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F));
        partdefinition2.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("froghat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.45F)), PartPose.ZERO);
        partdefinition.addOrReplaceChild("froghateye1", CubeListBuilder.create().texOffs(28, 54).addBox(-4.5F, -12.5F, -3.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
        partdefinition.addOrReplaceChild("froghateye2", CubeListBuilder.create().texOffs(28, 59).addBox(1.5F, -12.5F, -3.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Disguisager disguisager, float p_102929_, float p_102930_, float p_102931_, float p_102932_, float p_102933_)
    {
        this.root().getAllParts().forEach(ModelPart -> ModelPart.visible = !this.isDisguisagerDisguised);
        if (!this.isDisguisagerDisguised)
        {
            if (this.isDisguisagerBlowingHorn) {
                this.arms.visible = false;
                this.leftArm.visible = true;
                this.rightArm.visible = true;
                this.rightArm.xRot = 4.0f;
                this.rightArm.zRot = 0.2f;
            } else {
                super.setupAnim(disguisager, p_102929_, p_102930_, p_102931_, p_102932_, p_102933_);
            }
        }
        this.froghat.visible = true;
        this.froghateye1.visible = true;
        this.froghateye2.visible = true;
        this.froghat.setRotation(this.getHead().xRot, this.getHead().yRot, this.getHead().zRot);
        this.froghateye1.setRotation(this.getHead().xRot, this.getHead().yRot, this.getHead().zRot);
        this.froghateye2.setRotation(this.getHead().xRot, this.getHead().yRot, this.getHead().zRot);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        if (this.isDisguisagerDisguised)
        {
            poseStack.translate(0, 1.8f, 0);
        }
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.froghat.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.froghateye1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.froghateye2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}