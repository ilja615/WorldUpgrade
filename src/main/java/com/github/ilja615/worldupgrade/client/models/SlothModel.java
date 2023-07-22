package com.github.ilja615.worldupgrade.client.models;

import com.github.ilja615.worldupgrade.entity.Sloth;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SlothModel<T extends Sloth> extends EntityModel<Sloth>
{
	private final ModelPart arm1;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart arm2;
	private final ModelPart bb_main;

	public SlothModel(ModelPart root) {
		this.arm1 = root.getChild("arm1");
		this.leg1 = root.getChild("leg1");
		this.leg2 = root.getChild("leg2");
		this.arm2 = root.getChild("arm2");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(24, 59).addBox(0.0F, -3.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(60, 8).addBox(1.0F, 17.0F, -3.0F, 5.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 18.0F, -10.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 49).addBox(0.0F, -3.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(60, 0).addBox(1.0F, 17.0F, -3.0F, 5.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 18.0F, 8.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(36, 33).addBox(-6.0F, -3.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(-6.0F, 17.0F, -3.0F, 5.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 18.0F, 8.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition arm2 = partdefinition.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(54, 53).addBox(-6.0F, -3.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-6.0F, 17.0F, -3.0F, 5.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 18.0F, -10.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -13.0F, -12.0F, 18.0F, 9.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(-5.0F, -15.0F, -20.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 85).addBox(-9.0F, -4.0F, -12.0F, 18.0F, 3.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(60, 33).addBox(-5.0F, -7.0F, -20.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Sloth entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{	arm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		arm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}